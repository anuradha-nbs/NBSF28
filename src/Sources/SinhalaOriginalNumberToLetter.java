package Sources;

import java.text.DecimalFormat;

public class SinhalaOriginalNumberToLetter {

    String Number = null;
    int censPara = 0;

    public String getNumber(String Number) {
        System.out.println("Number "+Number);
        System.out.println("============");
        DecimalFormat formatter = new DecimalFormat("#0.##");
        //  String Cen=formatter.format(Double.parseDouble(Number)).substring(Number.length()-3);
        this.Number = Number;
        String Num = null;
        //Defining variables q is quotient, r is remainder

        int len, len2, q = 0, r = 0;
        String ltr = " ";
        String ltr2 = " ";
        String Str = "";
        String Str2 = "";
        SinhalaConstantNumberToLetter n = new SinhalaConstantNumberToLetter();
        double fullNum = Double.parseDouble(Number);
        //     int num = Integer.parseInt(Number);
        System.out.println("Full "+fullNum);
        int num = (int) fullNum;
        double fullNum2 = Double.parseDouble(Integer.toString(num));
        double deciVal = (fullNum - fullNum2) * 100;
        System.out.println("DEci............val" + formatter.format(deciVal));

        int num2 = Integer.parseInt(formatter.format(deciVal));

        if (deciVal == 0) {
            censPara = 1;

        } else if (deciVal > 0) {
            censPara = 2;

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            while (num2 > 0) {
                ////System.out.println(num2);
                len2 = n.numberCount(num2);
                
                //Take the length of the number and do letter conversion
                switch (len2) {
                    case 9:
                    case 8:
                        q = num2 / 10000000;
                        r = num2 % 10000000;
                        ltr = n.twonum2(q, 1);
                        if (r == 0) {
                            Str2 = Str2 + ltr2 + n.digit[4];
                            //System.out.println("H1.." + n.digit[4]);
                        } else if (r != 0) {
                            Str2 = Str2 + ltr2 + n.digit2[4];
                            //System.out.println("H1.." + n.digit2[4]);
                        }

                        num2 = r;
                        break;

                    case 7:
                    case 6:
                        q = num2 / 100000;
                        r = num2 % 100000;
                        ltr2 = n.twonum2(q, 1);
                        if (r == 0) {
                            Str2 = Str2 + ltr2 + n.digit[3];
                            //System.out.println("H2.." + n.digit[3]);
                        } else if (r != 0) {
                            Str2 = Str2 + ltr2 + n.digit2[3];
                            //System.out.println("H2.." + n.digit2[3]);
                        }

                        num2 = r;
                        break;

                    case 5:
                    case 4:

                        q = num2 / 1000;
                        r = num2 % 1000;
                        ltr2 = n.twonum2(q, 1);
                        if (r == 0) {
                            Str2 = Str2 + ltr2 + n.digit[2];
                            //System.out.println("H3.." + n.digit[2]);
                        } else if (r != 0) {
                            Str2 = Str2 + ltr2 + n.digit2[2];
                            //System.out.println("H3.." + n.digit2[2]);
                        }

                        num2 = r;
                        break;

                    case 3:

                        if (len2 == 3) {
                            r = num2;
                        }
                        ltr2 = n.threenum(r, 1);
                        Str2 = Str2 + ltr2;
                        num2 = 0;
                        break;

                    case 2:

                        ltr2 = n.twonum(num2, 1);
                        Str2 = Str2 + ltr2;
                        num2 = 0;
                        break;

                    case 1:
                        Str2 = Str2 + n.unitdo2[num2];
                        //System.out.println("I.." + n.unitdo2[num2]);
                        num2 = 0;
                        break;
                    default:

                        num2 = 0;
                        //System.out.println("Exceeding Crore....No conversion");

                }
                if (num2 == 0) {

                    //System.out.println(Str2 + " Rupees Only");
                    //Num="රුපියල්"+Str+" පමනයි.";  
                }

            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
        }

        if (num <= 0) {
            //System.out.println("Zero or Negative number not for conversion");
        }

        while (num > 0) {

            len = n.numberCount(num);
            //System.out.println("len "+len);
            //Take the length of the number and do letter conversion
            switch (len) {
                case 9:
                case 8:
                    q = num / 10000000;
                    r = num % 10000000;
                    ltr = n.twonum2(q, censPara);
                    if (r == 0) {
                        if (censPara == 1) {
                            Str = Str + ltr + n.digit[4];
                            //System.out.println("H1.." + n.digit[4]);
                        } else if (censPara == 2) {
                            Str = Str + ltr + n.digit3[4];
                            //System.out.println("H1.." + n.digit3[4]);
                        }

                    } else if (r != 0) {
                        Str = Str + ltr + n.digit2[4];
                        //System.out.println("H1.." + n.digit2[4]);
                    }

                    num = r;
                    break;

                case 7:
                case 6:
                    q = num / 100000;
                    r = num % 100000;
                    ltr = n.twonum2(q, censPara);
                    if (r == 0) {

                        if (censPara == 1) {
                            Str = Str + ltr + n.digit[3];
                            //System.out.println("H2.." + n.digit[3]);
                        } else if (censPara == 2) {
                            Str = Str + ltr + n.digit3[3];
                            //System.out.println("H2.." + n.digit3[3]);
                        }

                    } else if (r != 0) {
                        Str = Str + ltr + n.digit2[3];
                        //System.out.println("H2.." + n.digit2[3]);
                    }

                    num = r;
                    break;

                case 5:
                case 4:

                    q = num / 1000;
                    r = num % 1000;
                    ltr = n.twonum2(q, censPara);
                    if (r == 0) {

                        if (censPara == 1) {
                            Str = Str + ltr + n.digit[2];
                            //System.out.println("H3.." + n.digit[2]);
                        } else if (censPara == 2) {
                            Str = Str + ltr + n.digit3[2];
                            //System.out.println("H3.." + n.digit3[2]);
                        }

                    } else if (r != 0) {
                        Str = Str + ltr + n.digit2[2];
                        //System.out.println("H3.." + n.digit2[2]);
                    }

                    num = r;
                    break;

                case 3:

                    if (len == 3) {
                        r = num;
                    }
                    ltr = n.threenum(r, censPara);
                    Str = Str + ltr;
                    num = 0;
                    break;

                case 2:

                    ltr = n.twonum(num, censPara);
                    Str = Str + ltr;
                    num = 0;
                    break;

                case 1:

                    if (censPara == 1) {
                        Str = Str + n.unitdo2[num];
                        //System.out.println("I.." + n.unitdo2[num]);
                    } else if (censPara == 2) {
                        Str = Str + n.unitdo3[num];
                        //System.out.println("I.." + n.unitdo3[num]);
                    }

                    num = 0;
                    break;
                default:

                    num = 0;
                    //System.out.println("Exceeding Crore....No conversion");
//                    System.exit(1);

            }
            //System.out.println(num);
            if (num == 0 && censPara == 1) {

                //System.out.println(Str + " Rupees Only");
                Num = Str + " පමණයි.";  //"රුපියල්"+
            } else if (num == 0 && censPara == 2) {

                //System.out.println(Str + " Rupees Only");
                Num = Str + Str2 + " පමණයි.";  //"රුපියල්"+
            }

        }
        return Num;
    }

    public static void main(String[] args) {
        new SinhalaOriginalNumberToLetter().getNumber("123456789.23");
    }

}
