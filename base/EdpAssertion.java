package com.sunpower.automation.edp.api.base;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sunpower.automation.api.assertions.RestAssert;
import com.sunpower.automation.core.enums.LogLevel;
import com.sunpower.automation.core.utils.GsonUtils;
import com.sunpower.automation.core.utils.LogUtil;
import com.sunpower.automation.core.utils.StringUtil;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EdpAssertion {

    private Response response = null;

    private Object actual = null;

    private EdpAssertion(Response response) {
        this.response = response;
    }

    /**
     * assertThat
     *
     * @return
     */
    public static EdpAssertion doAssert(Response response) {
        return new EdpAssertion(response);
    }

    /**
     * Assert the response code is 200
     * @return
     */
    public EdpAssertion is200() {
        RestAssert.assertThat(response).responseCodeIs200();
        return this;
    }

    /**
     * Assert the response code is 400
     * @return
     */
    public EdpAssertion is400() {
        RestAssert.assertThat(response).responseCodeIs400();
        return this;
    }

    /**
     * Assert the response code is 404
     * @return
     */
    public EdpAssertion is404() {
        RestAssert.assertThat(response).responseCodeIs404();
        return this;
    }

    /**
     * Assert the response code is 401
     * @return
     */
    public EdpAssertion is401() {
        RestAssert.assertThat(response).responseCodeIs401();
        return this;
    }

    /**
     * Assert the response code is 204
     * @return
     */
    public EdpAssertion is204() {
        RestAssert.assertThat(response).responseCodeIs204();
        return this;
    }

    /**
     * Assert the response code is 201
     * @return
     */
    public EdpAssertion is201() {
        RestAssert.assertThat(response).responseCodeIs201();
        return this;
    }

    /**
     * Get the body of the Response
     * @return
     */
    public EdpAssertion getBodyAsString() {
        actual = response.body().asString();
        return this;
    }

    /**
     * Get the JSON map object by key
     * @param key
     * @return
     */
    public EdpAssertion getObject(String key) {
        Assertions.assertThat(key).as("Key should not be null").isNotNull();
        actual = response.jsonPath().getJsonObject(key);
        return this;
    }

    /**
     * Get Object and convert into Map based on the POJO class type
     *
     * @param key , Clazz type
     * @return
     */
    public EdpAssertion getObjectAndConvertToMap(String key, Class clazz) {
        Assertions.assertThat(key).as("Key should not be null").isNotNull();
        actual = GsonUtils.objectToMap(GsonUtils.jsonStringToObject(new Gson()
                .toJson(response.jsonPath().getJsonObject(key), LinkedHashMap.class), clazz));
        return this;
    }

    /**
     * Get the JSON list object by key
     * @return
     */
    public EdpAssertion getList(String key) {
        Assertions.assertThat(key).as("Key should not be null").isNotNull();
        actual = response.jsonPath().getList(key);
        return this;
    }

    /**
     * Assert the list size is not zero
     * @param description
     * @return
     */
    public EdpAssertion isListSizeNotZero(String description) {
        Assertions.assertThat(actual).as("Set value before accessing 'isListSizeNotZero() method").isNotNull();
        Assertions.assertThat(((List) actual).size()).as(description).isGreaterThan(0);
        return this;
    }

    /**
     * Assert the size is not zero
     * @param description
     * @return
     */
    public EdpAssertion isSizeNotZero(String description) {
        Assertions.assertThat(actual).as("Set value before accessing 'isSizeNotZero() method").isNotNull();
        Assertions.assertThat((Integer) actual > 0).as(description).isTrue();
        return this;
    }

    /**
     * Assert response body root contains passed keys
     * @param expectedKeys
     */
    public EdpAssertion responseBodyRootContainsKeys(List expectedKeys) {
        Assertions.assertThat(expectedKeys).as("List should not be null").isNotNull();
        RestAssert.assertThat(response).responseBodyRootContainsKeys(expectedKeys);
        return this;
    }

    /**
     * Assert the expected value is equal to actual value
     * @param description
     * @param expected
     * @return
     */
    public EdpAssertion isEqual(String description, Object expected) {
        Assertions.assertThat(actual).as(description).isEqualTo(expected);
        return this;
    }

    /**
     * Assert the expected value is equal to actual value and excluding a few items
     *
     * @param expected
     */
    public EdpAssertion isEqualExcludingItems(Map<String, Object> expected, List ignoreItems) {

        Assertions.assertThat(actual).as("Set value before accessing 'isEqual()' method").isNotNull();
        Assertions.assertThat(ignoreItems).as("Items should not be null").isNotNull().hasSizeGreaterThan(0);
        Assertions.assertThat(expected).as("Expected value should not be null or empty").isNotNull().hasSizeGreaterThan(0);
        expected.keySet().removeAll(ignoreItems);
        isEqual(expected);
        return this;
    }

    /**
     * Assert the expected value is equal to actual value and only a few items
     *
     * @param expected
     */
    public EdpAssertion isEqualOnlyItems(Map<String, Object> expected, List onlyItems) {

        Assertions.assertThat(onlyItems).as("Items should not be null").isNotNull().hasSizeGreaterThan(0);
        Assertions.assertThat(expected).as("Expected value should not be null or empty").isNotNull().hasSizeGreaterThan(0);
        expected.keySet().removeIf((data) -> !onlyItems.contains(data));
        isEqual(expected);
        return this;
    }

    /**
     * Assert the expected value is equal to actual value as Map type
     *
     * @param expected
     */
    public EdpAssertion isEqual(Map<String, Object> expected) {

        Assertions.assertThat(actual).as("Set value before accessing 'isEqual()' method").isNotNull();
        Assertions.assertThat(expected).as("Expected value should not be null or empty").isNotNull().hasSizeGreaterThan(0);
        expected.values().removeIf(data -> data == null || !StringUtil.hasProperValue(String.valueOf(data)));
        ((Map) actual).values().removeIf(data -> data == null || !StringUtil.hasProperValue(String.valueOf(data)));
        printReportLogs(Maps.difference((Map) actual, expected));
        return this;
    }

    /**
     * Asserting the parameter condition
     * description
     */
    public EdpAssertion isValid(boolean condition, String description) {

        Assertions.assertThat(condition).as(description).isTrue();
        return this;
    }

    /**
     * Assert the body of the response contains key
     * description
     */
    public EdpAssertion containsBody(String description, Object expected) {

        Assertions.assertThat(String.valueOf(actual).contains((String) expected)).as(description).isTrue();
        return this;
    }

    /**
     * Assert the actual content is proper value
     * @param description
     * @return
     */
    public EdpAssertion hasProperValue(String description) {

        Assertions.assertThat(hasProperValue()).as(description).isTrue();
        return this;
    }

    /**
     * Assert the list is equal to passing value
     * @param size
     * @return
     */
    public EdpAssertion isListSizeEqualTo(int size, String description) {
        Assertions.assertThat(actual).as("Set value before accessing 'isSizeEqualTo() method").isNotNull();
        Assertions.assertThat((List) actual).as(description).size().isEqualTo(size);
        return this;
    }

    /**
     * @param keys
     */
    public EdpAssertion containsKeys(List<String> keys) {

        Assertions.assertThat(keys).as("'Keys' should not be null").isNotNull();
        keys.stream().forEach(data -> containsKey(data, data + " value is not available"));
        return this;
    }

    /**
     * @param key
     * @param description
     */
    public EdpAssertion containsKey(String key, String description) {
        Assertions.assertThat(actual).as("Set value before accessing 'containKey() method").isNotNull();
        Assertions.assertThat(((Map) actual).containsKey(key)).as(description).isTrue();
        return this;
    }

    /**
     *
     */
    private boolean hasProperValue() {

        if (actual instanceof String || actual instanceof Boolean || actual instanceof Number) {
            return StringUtil.hasProperValue(actual.toString());
        } else if (actual instanceof List) {
            return ((List<?>) actual).stream().anyMatch((data) -> StringUtil.hasProperValue(String.valueOf(data)));
        } else if (actual instanceof Map) {
            return ((Map<String, ?>) actual).values().stream()
                    .anyMatch((value) -> StringUtil.hasProperValue(String.valueOf(value)));
        }
        return false;
    }

    /**
     * To print the report found
     *
     * @param foundDiff
     */
    private void printReportLogs(MapDifference<String, Object> foundDiff) {

        if (foundDiff.entriesOnlyOnRight().size() > 0) {
            LogUtil.log("***** WARNING!!! ***** \n"
                    + " The below values are not available in the actual \n"
                    + foundDiff.entriesOnlyOnRight().toString(), LogLevel.HIGH);
        }
        if (foundDiff.entriesInCommon().size() > 0) {
            LogUtil.log("The below values are checked with actual \n"
                    + foundDiff.entriesInCommon().toString(), LogLevel.HIGH);
        }
        Assertions.assertThat(foundDiff.entriesDiffering().size() == 0).as(() -> "The below "
                + "expected values are not matching with actual...! \n"
                + foundDiff.entriesDiffering().toString()).isTrue();
    }
}
