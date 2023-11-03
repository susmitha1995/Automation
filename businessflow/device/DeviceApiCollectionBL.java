package com.sunpower.automation.edp.api.businessflow.device;

import com.sunpower.automation.core.utils.JsonUtil;
import com.sunpower.automation.edp.api.enums.ResponseEnum;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeviceApiCollectionBL {

    private static DeviceApiCollectionBL deviceApiCollectionBL = null;

    private DeviceApiCollectionBL() {

    }

    protected final static DeviceApiCollectionBL createInstance() {
        if (deviceApiCollectionBL == null) {
            deviceApiCollectionBL = new DeviceApiCollectionBL();
        }
        return deviceApiCollectionBL;
    }

    public Response response;

    /**
     * @param token
     * @param deviceEnum
     * @param responseEnum
     * @param classItem
     * @param <T>
     * @return
     */
    public <T> T getMethod(String token, String deviceEnum, ResponseEnum responseEnum, Class<T> classItem) {

        response = given().header("Authorization", token).log().all()
                .get(deviceEnum).then().contentType(ContentType.JSON).assertThat()
                .statusCode(responseEnum.getValue()).extract().response();

        return JsonUtil.getObject(response.getBody().asString(), classItem);

    }
}
