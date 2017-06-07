/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.rest.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import sample.data.rest.SampleDataRestApplication;
import sample.data.rest.domain.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.data.rest.domain.Hotel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Oliver Gierke
 * @author Andy Wilkinson
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleDataRestApplication.class)
public class HotelRepositoryIntegrationTests {

    @Autowired
    HotelRepository repository;
    @Autowired
    CityRepository cityRepository;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Hotel setup ===");
    }


    @Test
    public void findsFirstPageOfCities() {
        System.out.println("=== hotal findsFirstPageOfCities ===");
        Page<Hotel> hotels = this.repository.findAll(new PageRequest(0, 10));
        assertThat(hotels.getTotalElements()).isGreaterThan(20L);
    }

    @Test
    public void findByCity() {
        System.out.println("=== hotal findByCity ===");
        City city = cityRepository.findByNameAndCountryAllIgnoringCase("Melbourne",
                "Australia");
        Hotel hotel = this.repository.findByCity(city);
        assertThat(hotel).isNotNull();
        assertThat(hotel.getName()).isEqualTo("The Langham");
    }


}
