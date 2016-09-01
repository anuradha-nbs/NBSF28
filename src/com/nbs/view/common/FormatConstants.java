/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nbs.view.common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author mmh
 */
public class FormatConstants {
    public static NumberFormat numberFormat1 = new DecimalFormat("#,##0.00");
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
}
