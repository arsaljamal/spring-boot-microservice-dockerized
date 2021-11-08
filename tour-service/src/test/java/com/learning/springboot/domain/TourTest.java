package com.learning.springboot.domain;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TourTest {

    @Test
    public void testConstructorAndGetters() throws Exception {
        TourPackage tourPackage = new TourPackage("PK","karachi-tour");
        Tour tour = new Tour("Karachi",tourPackage,Difficulty.Difficult, Region.Sindh);

        assertNull(tour.getId());
        assertThat(tour.getTitle(), is("Karachi"));
        assertThat(tour.getDifficulty(), is(Difficulty.Difficult));
        assertThat(tour.getRegion(), is(Region.Sindh));
        assertThat(tour.getTourPackage().getCode(), is("PK"));
        assertThat(tour.getTourPackage().getName(), is("karachi-tour"));
    }

    @Test
    public void equalsHashCodeVerify() {
        TourPackage tourPackage = new TourPackage("PK","karachi-tour");
        Tour tour1 = new Tour("Karachi",tourPackage,Difficulty.Difficult, Region.Sindh);
        Tour tour2 = new Tour("Karachi",tourPackage,Difficulty.Difficult, Region.Sindh);

        assertThat(tour1,is(tour2));
        assertThat(tour1.hashCode(),is(tour2.hashCode()));
    }
}
