package sch.xmut.wu.apicourt.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wu on 2020/05/01
 */
public class OssImageResponse extends BaseResponse {
    @JsonProperty("image_url")
    private String iamgeUrl;

    public String getIamgeUrl() {
        return iamgeUrl;
    }

    public void setIamgeUrl(String iamgeUrl) {
        this.iamgeUrl = iamgeUrl;
    }
}
