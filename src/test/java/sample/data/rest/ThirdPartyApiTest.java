package sample.data.rest;


import org.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class ThirdPartyApiTest {

    final RestTemplate template = new RestTemplate();

    @Test
    @Ignore
    public void testCitiesSearch() throws Exception {
        String api = "http://localhost:8000/api/cities/search/findByNameAndCountryAllIgnoringCase";
        String params = "?name=Melbourne&country=Australia";
        String url = api + params;

        String message = template.getForObject(url, String.class);
        JSONObject jsonObj = new JSONObject(message);
        assertThat(jsonObj.has("country")).isTrue();
    }


}
