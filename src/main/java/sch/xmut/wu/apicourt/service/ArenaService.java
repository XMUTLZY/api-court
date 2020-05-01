package sch.xmut.wu.apicourt.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import sch.xmut.wu.apicourt.entity.ApiOssEntity;
import sch.xmut.wu.apicourt.entity.ArenaEntity;
import sch.xmut.wu.apicourt.entity.CourtEntity;
import sch.xmut.wu.apicourt.http.request.ArenaRequest;
import sch.xmut.wu.apicourt.http.response.ArenaResponse;
import sch.xmut.wu.apicourt.http.response.BaseResponse;
import sch.xmut.wu.apicourt.http.response.LayerResponse;
import sch.xmut.wu.apicourt.http.response.OssImageResponse;
import sch.xmut.wu.apicourt.http.vo.ApiOss;
import sch.xmut.wu.apicourt.http.vo.Arena;
import sch.xmut.wu.apicourt.http.vo.Court;
import sch.xmut.wu.apicourt.repository.ApiOssRepository;
import sch.xmut.wu.apicourt.repository.ArenaRepository;
import sch.xmut.wu.apicourt.repository.CourtRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by wu on 2020/04/13
 */
@Service
public class ArenaService {
    public static final String BUCKET_NAME = "image-steganography";
    public static final String IMAGE_FOLDER = "image/";
    public static final String RESULT_IMAGE_FOLDER = "resultImage/";
    public static final String RESULT_IMAGE_STYLE2 = "x-oss-process=style/resultImage_style2";
    @Autowired
    private ArenaRepository arenaRepository;
    @Autowired
    private CourtRepository courtRepository;
    @Autowired
    private ApiOssRepository apiOssRepository;

    public ArenaResponse list(ArenaRequest request) {
        ArenaResponse response = new ArenaResponse();
        List<ArenaEntity> arenaEntityList;
        List<Arena> arenaList = new ArrayList<>();
        if (request.getListType() == 0) {
            arenaEntityList = arenaRepository.findAllByIsRecommend(ArenaEntity.RECOMMEND_YES);
        } else {
            arenaEntityList = arenaRepository.findAll();
        }
        buildVoByArenaEntity(arenaEntityList, arenaList);
        if (request.getListType() == 0) {
            response.setArenaList(arenaList);
            return response;
        } else if (request.getListType() == 1) {
            Collections.sort(arenaList, new Comparator<Arena>() {
                @Override
                public int compare(Arena o1, Arena o2) {
                    double diff = o1.getTotalScore() - o2.getTotalScore();
                    if (diff > 0.0) {
                        return -1;
                    } else if (diff < 0.0) {
                        return 1;
                    }
                    return 0;
                }
            });
        } else if (request.getListType() == 2) {
            Collections.sort(arenaList, new Comparator<Arena>() {
                @Override
                public int compare(Arena o1, Arena o2) {
                    double diff = o1.getSingleScore() - o2.getSingleScore();
                    if (diff > 0.0) {
                        return -1;
                    } else if (diff < 0.0) {
                        return 1;
                    }
                    return 0;
                }
            });
        } else {
            Collections.sort(arenaList, new Comparator<Arena>() {
                @Override
                public int compare(Arena o1, Arena o2) {
                    double diff = o1.getPrice() - o2.getPrice();
                    if (diff > 0.0) {
                        return 1;
                    } else if (diff < 0.0) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        response.setArenaList(arenaList);
        return response;
    }

    public void buildVoByArenaEntity(List<ArenaEntity> arenaEntityList, List<Arena> arenaList) {
        for (ArenaEntity arenaEntity : arenaEntityList) {
            Arena arena = new Arena();
            BeanUtils.copyProperties(arenaEntity, arena);
            List<CourtEntity> courtEntityList = courtRepository.findAllByArenaId(arenaEntity.getId());//查询该球馆的所有球场
            if (!CollectionUtils.isEmpty(courtEntityList)) {
                Double countTemp1 = 0.0;
                Double countTemp2 = 0.0;
                Double countTemp3 = 0.0;
                for (CourtEntity courtEntity : courtEntityList) {
                    //球馆评分计算方式
                    countTemp1 = countTemp1 + courtEntity.getScore();
                    //球馆综合分计算方式
                    countTemp2 = countTemp2 + (courtEntity.getRentWork() + courtEntity.getRentWeekend())/2 - courtEntity.getScore()*10;
                    //球馆均价计算方式
                    countTemp3 = countTemp3 + (courtEntity.getRentWork() + courtEntity.getRentWeekend())/2;
                }
                arena.setSingleScore(countTemp1/courtEntityList.size());
                arena.setTotalScore(countTemp2/courtEntityList.size());
                arena.setPrice(countTemp3/courtEntityList.size());
            }
            arenaList.add(arena);
        }
    }

    public ArenaResponse detail(ArenaRequest request) {
        ArenaResponse response = new ArenaResponse();
        Optional<ArenaEntity> arenaEntityOptional = arenaRepository.findById(request.getArenaId());
        if (arenaEntityOptional.isPresent()) {
            Arena arena = new Arena();
            BeanUtils.copyProperties(arenaEntityOptional.get(), arena);
            response.setArena(arena);
        }
        List<CourtEntity> courtEntityList = courtRepository.findAllByArenaId(request.getArenaId());
        List<Court> courtList = new ArrayList<>();
        for (CourtEntity courtEntity : courtEntityList) {
            Court court = new Court();
            BeanUtils.copyProperties(courtEntity, court);
            courtList.add(court);
        }
        response.setCourtList(courtList);
        return response;
    }

    public ArenaResponse search(ArenaRequest request) {
        ArenaResponse response = new ArenaResponse();
        List<ArenaEntity> arenaEntityList = arenaRepository.findAllByNameLike(request.getArenaName());
        List<Arena> arenaList = new ArrayList<>();
        buildVoByArenaEntity(arenaEntityList, arenaList);
        response.setArenaList(arenaList);
        return response;
    }

    public LayerResponse getArenaList(Pageable pageable) {
        LayerResponse response = new LayerResponse();
        Page<ArenaEntity> arenaEntityPage = arenaRepository.findAll(pageable);
        List<Arena> arenaList = new ArrayList<>();
        for (ArenaEntity arenaEntity : arenaEntityPage) {
            Arena arena = new Arena();
            BeanUtils.copyProperties(arenaEntity, arena);
            if (arenaEntity.getStatus() == ArenaEntity.STATUS_REST) {
                arena.setStatusStr("休息");
            } else if (arenaEntity.getStatus() == ArenaEntity.STATUS_BUSY) {
                arena.setStatusStr("营业中");
            } else {
                arena.setStatusStr("倒闭(删除)");
            }
            if (arenaEntity.getIsRecommend() == ArenaEntity.RECOMMEND_YES) {
                arena.setRecommendStr("推荐");
            } else {
                arena.setRecommendStr("不推荐");
            }
            arenaList.add(arena);
        }
        List<ArenaEntity> list = arenaRepository.findAll();
        response.setCount(list.size());
        response.setData(arenaList);
        return response;
    }

    public BaseResponse arenaAdd(ArenaRequest request) {
        ArenaEntity arenaEntity = new ArenaEntity();
        BeanUtils.copyProperties(request, arenaEntity);
        arenaEntity.setName(request.getArenaName());
        arenaEntity.setStartTime(new Date());
        arenaEntity.setEndTime(new Date());
        arenaRepository.save(arenaEntity);
        return new BaseResponse();
    }

    public BaseResponse arenaDelete(Integer id) {
        Optional<ArenaEntity> arenaEntityOptional = arenaRepository.findById(id);
        ArenaEntity arenaEntity = arenaEntityOptional.get();
        arenaEntity.setStatus(ArenaEntity.STATUS_DELETE);
        arenaRepository.save(arenaEntity);
        return new BaseResponse();
    }

    //文件上传
    public OssImageResponse imageUploadOss(MultipartFile file) {
        OssImageResponse ossImageResponse = new OssImageResponse();
        ApiOss apiOss = getApiOssInfo();
        OSSClient ossClient = new OSSClient(apiOss.getEndPoint(), apiOss.getAccessKey(), apiOss.getAccessSecret());
        String resultImageUrl = null;
        String fileName = file.getOriginalFilename();
        String imageName = UUID.randomUUID().toString() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        Long fileSize = file.getSize();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentEncoding("utf-8");
        metadata.setContentType(getContentType(imageName));
        metadata.setContentDisposition("filename/filesize=" + imageName + "/" + fileSize + "Byte.");
        //上传文件
        try {
            ossClient.putObject(BUCKET_NAME, IMAGE_FOLDER + imageName, file.getInputStream(), metadata);
            resultImageUrl = "http://" + BUCKET_NAME + "." + apiOss.getEndPoint() + "/" + IMAGE_FOLDER + imageName;
        } catch (IOException e) {
        }
        ossImageResponse.setIamgeUrl(resultImageUrl);
        return ossImageResponse;
    }

    public ApiOss getApiOssInfo() {
        List<ApiOssEntity> apiOssEntityList = apiOssRepository.findAll();
        if (CollectionUtils.isEmpty(apiOssEntityList)) {
            return null;
        }
        ApiOssEntity apiOssEntity = apiOssEntityList.get(0);
        String aesKey = apiOssEntity.getEncrypt();
        byte[] aesByte = Base64.decodeBase64(aesKey);
        ApiOss apiOss = new ApiOss();
        BeanUtils.copyProperties(apiOssEntity, apiOss);
        apiOss.setAccessSecret(aesDecode(aesByte, apiOssEntity.getAccessSecret()));
        return apiOss;
    }

    /**
     * AES解密算法
     */
    public static String aesDecode(byte[] bytes, String info) {
        AesCipherService aesCipherService = new AesCipherService();
        return new String(aesCipherService.decrypt(Hex.decode(info), bytes).getBytes());
    }

    private String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        return null;
    }
}
