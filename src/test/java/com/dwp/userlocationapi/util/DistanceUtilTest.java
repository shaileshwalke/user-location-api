package com.dwp.userlocationapi.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
 public class DistanceUtilTest {

    private double londonLatitude = 51.50853;
    private double londonLongitude = -0.12574;

    @Test
    public void distanceTest () {
        double distance = DistanceUtil.calculatorDistance(londonLatitude,londonLongitude,34.003135,-117.7228641);
        Assert.assertNotNull(distance);
    }


}