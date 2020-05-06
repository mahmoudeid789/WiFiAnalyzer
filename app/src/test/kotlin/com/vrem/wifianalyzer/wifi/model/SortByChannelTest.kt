/*
 * WiFiAnalyzer
 * Copyright (C) 2020  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.vrem.wifianalyzer.wifi.model

import com.vrem.util.STRING_EMPTY
import com.vrem.wifianalyzer.wifi.band.WiFiWidth
import org.junit.Assert
import org.junit.Test

class SortByChannelTest {
    private val fixture = sortByChannel()

    @Test
    fun testSortByChannel() {
        // setup
        val wiFiDetail1 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        val wiFiDetail2 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2432, WiFiWidth.MHZ_40, -55, false),
                WiFiAdditional.EMPTY)
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        Assert.assertEquals(0, actual)
    }

    @Test
    fun testSortByChannelWithDifferentChannel() {
        // setup
        val wiFiDetail1 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        val wiFiDetail2 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2432, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        Assert.assertEquals(1, actual)
    }

    @Test
    fun testSortByChannelWithDifferentSSID() {
        // setup
        val wiFiDetail1 = WiFiDetail(
                WiFiIdentifier("ssid1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        val wiFiDetail2 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        Assert.assertEquals(32, actual)
    }

    @Test
    fun testSortByChannelWithDifferentBSSID() {
        // setup
        val wiFiDetail1 = WiFiDetail(
                WiFiIdentifier("SSID1", "bssid1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        val wiFiDetail2 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        Assert.assertEquals(32, actual)
    }

    @Test
    fun testSortByChannelWithDifferentStrength() {
        // setup
        val wiFiDetail1 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -55, true),
                WiFiAdditional.EMPTY)
        val wiFiDetail2 = WiFiDetail(
                WiFiIdentifier("SSID1", "BSSID1"),
                STRING_EMPTY,
                WiFiSignal(2462, 2462, WiFiWidth.MHZ_20, -35, true),
                WiFiAdditional.EMPTY)
        // execute
        val actual: Int = fixture.compare(wiFiDetail1, wiFiDetail2)
        // validate
        Assert.assertEquals(1, actual)
    }
}