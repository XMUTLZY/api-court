package sch.xmut.wu.apicourt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sch.xmut.wu.apicourt.entity.ArenaEntity;
import sch.xmut.wu.apicourt.entity.CourtEntity;
import sch.xmut.wu.apicourt.http.request.ArenaRequest;
import sch.xmut.wu.apicourt.http.response.ArenaResponse;
import sch.xmut.wu.apicourt.http.vo.Arena;
import sch.xmut.wu.apicourt.http.vo.Court;
import sch.xmut.wu.apicourt.repository.ArenaRepository;
import sch.xmut.wu.apicourt.repository.CourtRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by wu on 2020/04/13
 */
@Service
public class ArenaService {
    @Autowired
    private ArenaRepository arenaRepository;
    @Autowired
    private CourtRepository courtRepository;

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
}
