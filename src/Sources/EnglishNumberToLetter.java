package Sources;

import java.text.DecimalFormat;

public class EnglishNumberToLetter

   {
     String Number=null;
     int censPara=0;
    public String getNumber(String Number) {
      DecimalFormat formatter = new DecimalFormat( "###########" );  
      this.Number=Number;  
          String Num=null;
          int len,len2, q=0, r=0;
          String ltr = " ";
          String ltr2 = " ";
          String Str = "";
          String Str2 = "";
          EnglishConstNumtoLetter n = new EnglishConstNumtoLetter();
          double fullNum=Double.parseDouble(Number);
     //     int num = Integer.parseInt(Number);
          int num=(int) fullNum;
          double fullNum2=Double.parseDouble(Integer.toString(num));
          double deciVal=(fullNum-fullNum2)*100;
          //System.out.println("DEci............val.."+formatter.format(deciVal));
          
          int num2 = Integer.parseInt(formatter.format(deciVal));
          
          if(deciVal==0){
          censPara=1;
              //System.out.println("Deci Val..0");
          }else if(deciVal>0){
          censPara=2;
              //System.out.println("Deci Val..1");
          }

          if (num <= 0) //System.out.println("Zero or Negative number not for conversion");

          while (num>0)
          {

             len = n.numberCount(num);

             //Take the length of the number and do letter conversion

             switch (len)

             {
                  case 8:
                       /*   q=num/10000000;
                          r=num%10000000;
                          ltr = n.twonum(q);
                          //System.out.println("ltr...1"+ltr);
                          Str = Str+ltr+n.digit[3];
                          //System.out.println("Str...1"+Str);
                          num = r;
                          break;   */

                  case 7:
                          q=num/1000000;
                          r=num%1000000;
                          ltr = n.twonum(q);
                          //System.out.println("ltr...1"+ltr);
                          Str = Str+ltr+n.digit[3];
                          //System.out.println("Str...1"+Str);
                          num = r;
                          break;
                  case 6:
                        /*  q=num/100000;
                          r=num%100000;
                          ltr = n.twonum(q);
                          //System.out.println("ltr...4"+ltr);
                          Str = Str+ltr+n.digit[3];
                          //System.out.println("Str...4"+Str);
                          num = r;
                          break;*/

                  case 5:
                  case 4:

                           q=num/1000;
                           r=num%1000;
                           ltr = n.twonum(q);
                           //System.out.println("ltr...2"+ltr);
                           Str= Str+ltr+n.digit[2];
                           //System.out.println("Str...2"+Str);
                           num = r;
                           break;

                  case 3:


                            if (len == 3)
                                r = num;
                            ltr = n.threenum(r,censPara);
                            //System.out.println("ltr...3"+ltr);
                            Str = Str + ltr;
                            //System.out.println("Str...3"+Str);
                            num = 0;
                            break;

                  case 2:

                           ltr = n.twonum(num);
                           Str = Str + ltr;
                           num=0;
                           break;

                  case 1:
                           Str = Str + n.unitdo[num];
                           num=0;
                           break;
                  default:

                          num=0;
                          //System.out.println("Exceeding Crore....No conversion");
                          System.exit(1);


              }
             
             
             if(censPara==1){
                          if (num==0){
                          //System.out.println(Str+" Rupees Only");
                          Num=Str+" Rupees Only";
                          }             
             }else if(censPara==2){
                          if (num==0){
                          //System.out.println(Str+" Rupees Only");
                          Num=Str+" Rupees and "+getCens(num2);
                          }             
             }

                          
            }
  return Num;
    }
    
    
public String getCens(int num){
          String Num=null;
          int len,len2, q=0, r=0;
          String ltr = " ";
          String ltr2 = " ";
          String Str = "";
          String StrCents = "";
EnglishConstNumtoLetter n = new EnglishConstNumtoLetter();          
while (num>0)
          {

             len = n.numberCount(num);

             //Take the length of the number and do letter conversion

             switch (len)

             {
                  case 8:
                          

                  case 7:
                          q=num/1000000;
                          r=num%1000000;
                          ltr = n.twonum(q);
                          Str = Str+ltr+n.digit[3];
                          num = r;
                          break;
                  case 6:
                        /*  q=num/100000;
                          r=num%100000;
                          ltr = n.twonum(q);
                          Str = Str+ltr+n.digit[3];
                          num = r;
                          break;*/

                  case 5:
                  case 4:

                           q=num/1000;
                           r=num%1000;
                           ltr = n.twonum(q);
                           Str= Str+ltr+n.digit[2];
                           num = r;
                           break;

                  case 3:


                            if (len == 3)
                                r = num;
                            ltr = n.threenum(r,censPara);
                            Str = Str + ltr;
                            num = 0;
                            break;

                  case 2:

                           ltr = n.twonum(num);
                           Str = Str + ltr;
                           num=0;
                           break;

                  case 1:
                           Str = Str + n.unitdo[num];
                           num=0;
                           break;
                  default:

                          num=0;
                          //System.out.println("Exceeding Crore....No conversion");
                          System.exit(1);


              }
             
             
             
                          if (num==0){
                          //System.out.println(Str+" Cents Only");
                          StrCents=Str+" Cents Only";
                          }             
             

                          
            }    
    return StrCents;
}    

 

      
    

      }
