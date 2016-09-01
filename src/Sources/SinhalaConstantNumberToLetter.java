package Sources;

public class SinhalaConstantNumberToLetter {

    String[] unitdo = {"", " එක්", " දෙ", " තුන් ", " හාර ", " පන් ",
        " හය ", " හත් ", " අට ", " නම ", " දස ", " එකොලොස් ", " දොලොස් ",
        " දහතුන් ", " දාහතර ", " පහළොස් ", " දාසය ", " දාහත් ",
        " දහ අට ", " දහ නම "};
    String[] unitdo2 = {"", " එකක්", " දෙකක්", " තුනක්", " හතරක්", " පහක්",
        " හයක්", " හතක්", " අටක්", " නමයක්", " දහයක්", " එකොලහක්", " දොලහක්",
        " දහතුනක්", " දාහතරක්", " පහලවක්", " දාසයක්", " දාහතක්",
        " දහඅටක්", " දහනමයක්"};

    String[] unitdo3 = {"", " එකයි ශත", " දෙකයි ශත", " තුනයි ශත", " හතරයි ශත", " පහයි ශත",
        " හයයි ශත", " හතයි ශත", " අටයි ශත", " නමයයි ශත", " දහයයි ශත", " එකොලහයි ශත", " දොලහයි ශත",
        " දහතුනයි ශත", " දාහතරයි ශත", " පහලවයි ශත", " දාසයයි ශත", " දාහතයි ශත",
        " දහඅටයි ශත", " දහනමයයි ශත"};
    String[] tens = {"", "දහයක්", " විස්සක්", " තිහක්", " හතලිහක්", " පනහක්",
        " හැටක්", " හැත්තෑවක්", " අසූවක්", " අනූවක්"};

    String[] tens3 = {"", "දහයයි ශත", " විස්සයි ශත", " තිහයි ශත", " හතලිහයි ශත", " පනහයි ශත",
        " හැටයි ශත", " හැත්තෑවයි ශත", " අසූවයි ශත", " අනූවයි ශත"};
    String[] tens2 = {"", "දස", " විසි", " තිස්", " හතලිස්", " පනස්",
        " හැට", " හැත්තෑ", " අසූ", " අනූ"};
    String[] digit = {"", "සියයක්", "දහසක්", " ලක්ෂයක්", " කෝටියක්"};
    String[] digit3 = {"", "සියයයි ශත", "දහසයි ශත", " ලක්ෂයයි ශත", " කෝටියයි ශත"};
    String[] digit2 = {"", "සිය", "දහස්", "ලක්ෂ", "කෝටි"};
    int r;
    int censPara = 0;

    //Count the number of digits in the input number
    int numberCount(int num) {
        int cnt = 0;

        while (num > 0) {
            r = num % 10;
            cnt++;
            num = num / 10;
        }

        return cnt;
    }

    //Function for Conversion of two digit
    String twonum(int numq, int censPara) {

        int numr, nq;
        String ltr = "";
        this.censPara = censPara;

        if (censPara == 1) {

            nq = numq / 10;
            numr = numq % 10;

            if (numq > 19) {
                if (numr == 0) {
                    ltr = ltr + tens[nq] + unitdo2[numr];
                    //System.out.println("A.1." + tens[nq]);
                    //System.out.println("B.." + unitdo2[numr]);
                } else if (numr != 0) {
                    ltr = ltr + tens2[nq] + unitdo2[numr];
                    //System.out.println("A.2." + tens2[nq]);
                    //System.out.println("B.3." + unitdo2[numr]);
                }

            } else if (numq <= 19) {
                ltr = ltr + unitdo2[numq];
                //System.out.println("C.11." + unitdo2[numq]);
            } else {
                ltr = ltr + unitdo[numq];
                //System.out.println("C.22." + unitdo[numq]);
            }

        } else if (censPara == 2) {

            nq = numq / 10;
            numr = numq % 10;

            if (numq > 19) {
                if (numr == 0) {
                    ltr = ltr + tens3[nq] + unitdo3[numr];
                    //System.out.println("A.4." + tens3[nq]);
                    //System.out.println("B.5." + unitdo3[numr]);
                } else if (numr != 0) {
                    ltr = ltr + tens2[nq] + unitdo3[numr];
                    //System.out.println("A.6." + tens2[nq]);
                    //System.out.println("B.7." + unitdo3[numr]);
                }

            } else if (numq <= 19) {
                ltr = ltr + unitdo3[numq];
                //System.out.println("C.8." + unitdo3[numq]);
            } else {
                ltr = ltr + unitdo[numq];
                //System.out.println("C.9." + unitdo[numq]);
            }

        }

        return ltr;
    }

    String twonum2(int numq, int censPara) {
        int numr, nq;
        String ltr = "";
        this.censPara = censPara;

        if (censPara == 1) {
            nq = numq / 10;
            numr = numq % 10;

            if (numq > 19) {
                if (numr == 0) {
                    ltr = ltr + tens2[nq] + unitdo2[numr];
                    //System.out.println("A.10." + tens[nq]);
                    //System.out.println("B.11." + unitdo2[numr]);
                } else if (numr != 0) {
                    if (numr == 4) {
                        ltr = ltr + tens2[nq] + " හතර";
                        //System.out.println("A.12." + tens2[nq]);
                        //System.out.println("B.13." + " හතර");
                    } else {
                        ltr = ltr + tens2[nq] + unitdo[numr];
                        //System.out.println("A.14." + tens2[nq]);
                        //System.out.println("B.15." + unitdo[numr]);
                    }

                }

            } else {

                ltr = ltr + unitdo[numq];
                //System.out.println("C.16." + unitdo[numq]);

            }
        } else if (censPara == 2) {

            nq = numq / 10;
            numr = numq % 10;

            if (numq > 19) {
                if (numr == 0) {
                    ltr = ltr + tens2[nq] + unitdo3[numr];
                    //System.out.println("A.17." + tens3[nq]);
                    //System.out.println("B.18." + unitdo3[numr]);
                } else if (numr != 0) {
                    if (numr == 4) {
                        ltr = ltr + tens2[nq] + " හතර";
                        //System.out.println("A.19." + tens2[nq]);
                        //System.out.println("B.20." + " හතර");
                    } else {
                        ltr = ltr + tens2[nq] + unitdo[numr];
                        //System.out.println("A.21." + tens2[nq]);
                        //System.out.println("B.22." + unitdo[numr]);
                    }

                }

            } else {

                ltr = ltr + unitdo[numq];
                //System.out.println("C.28." + unitdo[numq]);

            }
        }

        return ltr;
    }

    //Function for Conversion of three digit
    String threenum(int numq, int censPara) {
        int numr, nq;
        String ltr = "";
        this.censPara = censPara;

        if (censPara == 1) {
            nq = numq / 100;
            numr = numq % 100;

            if (numr == 0) {
                ltr = ltr + unitdo[nq] + digit[1];
                //System.out.println("D.23." + unitdo[nq]);
                //System.out.println("E.24." + digit[1]);
            } else {
                ltr = ltr + unitdo[nq] + digit2[1] + twonum(numr, censPara);
                //System.out.println("F.25." + unitdo[nq]);
                //System.out.println("G.26." + digit[1]);
            }
        } else if (censPara == 2) {
            nq = numq / 100;
            numr = numq % 100;

            if (numr == 0) {
                ltr = ltr + unitdo[nq] + digit3[1];
                //System.out.println("D.23." + unitdo[nq]);
                //System.out.println("E.24." + digit3[1]);
            } else {
                ltr = ltr + unitdo[nq] + digit2[1] + twonum(numr, censPara);
                //System.out.println("F.25." + unitdo[nq]);
                //System.out.println("G.26." + digit3[1]);
            }
        }

        return ltr;

    }

}
