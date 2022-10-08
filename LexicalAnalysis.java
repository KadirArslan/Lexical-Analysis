/**
*
* @author Kadir ARSLAN 
* @author Semih DEMIRCI
*  Lexical Analysis For Java
*/

package LexicalAnalysis;
//Gerekli kutuphaneler eklendi.
import java.io.*;
import java.util.ArrayList; 

public class LexicalAnalysis {

    public static void main(String[] args) {
        //Fonksiyon degiskenlerini tutmak icin gerekli class tanimlandi.
        class functions{
            
            String functionName;
            String returnValue;
            int countOfParameter;
            String parameterValue;
        }
        //Programda kullanilacak degiskenler tanimlandi.
        int countOfVar = 0;
        int countOfFunction = 0;
        int countOfComma;
        int commentIndex = 0;
        int semicolonIndex;
        String[] arraySplitted;
        ArrayList arrayList = new ArrayList();
        functions functionObj = new functions();
        String str;
        //Dosya ismini tutan degisken tanimlandi.
        String fName = "Program.java";
        //Verilen klasor yolundaki dosya okunmaya baslandi.
            try{
                BufferedReader file = new BufferedReader(new FileReader(fName));
                //Dosya sonuna gelene kadar okuma devam ediyor.
                while((str = file.readLine()) != null) {
                    //Class isminin bulunmasi amaciyla kontrol yapiliyor.
                    if(str.contains("class")){ 
                        //Class ismi belirtilen formatta aliniyor. 
                        int nameIndex = str.indexOf(" ");
                        String name = str.substring(nameIndex+1);
                        
                        nameIndex = name.indexOf(" ");
                        
                        String className = name.substring(nameIndex+1);
                        
                        if(className.contains(" "))
                           className =  className.substring(0,className.length()-2);
                        
                        else if(className.contains("{"))
                           className = className.substring(0,className.length()-1);
                        
                        System.out.println("Class Name" + className);
                       
                    }
                    //   /* ... */   seklinde olan yorum araliklari hesaba katilmadan degisken ve fonksiyonlar
                    //   tespit ediliyor.
                    for(int i=0 ; i<str.length() ; i++){                       
                         
                        int slashIndex = str.indexOf("/",i);                                
                        int starIndex = str.indexOf("*",i);   
                        
                        if(slashIndex != -1 && starIndex != -1)
                            commentIndex = str.indexOf("*/",i);
                        
                        if (commentIndex == -1)
                            str=" ";
                     }
                     //Degisken tur kontrolleri yapiliyor.
                    if(str.contains("int")||str.contains("boolean")||str.contains("byte") 
                    ||str.contains("char")||str.contains("double")||str.contains("float")
                    ||str.contains("long")||str.contains("short")||str.contains("void")||str.contains("String")){
                        //Degisken sayisi hesaplanarak, degiskenler ArrayList'e ataniyor.
                        if(!str.contains("(") && !str.contains("//")){
                            countOfVar++;
                            arraySplitted = str.split(" ");
                            semicolonIndex = arraySplitted[arraySplitted.length-1].indexOf(";");
                            String b = arraySplitted[arraySplitted.length-1].substring(0,semicolonIndex);

                            String c =arraySplitted[arraySplitted.length-2];

                            arrayList.add(b+" - "+c);
                        }
                        //Fonksiyon Sayisi Hesaplaniyor.
                        if(str.contains("(") && str.contains(")") && !str.contains("return") && !str.contains("//") ){
                            countOfFunction++;
                        }
                    }    
                }
            // Try blogunda hata ile karsilasilirsa hata mesaji yazdiriliyor.
            }catch (IOException e){
                System.out.println("HATA ! ");
            }
            // Bulunan alt eleman sayisi yazdiriliyor.
            System.out.println("Sub-elements:"+countOfVar);
            // Degiskenlerin icinde bulundugu ArrayList yazdiriliyor.
            for(int i = 0;i<arrayList.size();i++)
                System.out.println(arrayList.get(i));
            //Uye fonksiyon sayisi yazdiriliyor.
            System.out.println("Functions Count:"+countOfFunction+"");
            //Verilen klasor yolundaki dosya okunmaya baslandi.
            try{
                BufferedReader file = new BufferedReader(new FileReader(fName));
                //Dosya sonuna gelene kadar okuma devam ediyor.
                while((str = file.readLine()) != null) {
                         //   /* ... */   seklinde olan yorum araliklari hesaba katilmadan degisken ve fonksiyonlar
                         //   tespit ediliyor.
                         for( int i=0;i<str.length();i++){ 
                           
                            int slashIndex=str.indexOf("/",i);         
                            int starIndex=str.indexOf("*",i);         
                           
                            if(slashIndex!=-1 && starIndex!=-1)
                                commentIndex=str.indexOf("*/",i);
                             
                            if(commentIndex==-1)
                                str=" ";
                         }
                         //Fonksiyon degerlerini bulmak icin gerekli kontrol yapiliyor.
                         if(str.contains("(") && str.contains(")") && !str.contains("return") && !str.contains("//")){

                             int leftParenthesis = str.indexOf("(");
                             int rightParenthesis = str.indexOf(")");

                             String splitValue = str.substring(0,leftParenthesis);

                             int blank = splitValue.lastIndexOf(" ");
                             int blank1 = splitValue.indexOf(" ");

                             String parameterValue = str.substring(leftParenthesis+1,rightParenthesis);
                             //Parametre olmamasi durumunda "Yok" ifadesi parametre degeri olarak ataniyor.
                             if(parameterValue.isEmpty()){
                                 parameterValue = "None";
                              } 

                             functionObj.parameterValue=parameterValue;
                             //Bulunan fonksiyondaki parametre sayisi hesaplaniyor.
                             boolean commaControl = parameterValue.contains(",");
                             
                             if(commaControl == true){
                                 String[] commaCount = parameterValue.split(",");
                                 countOfComma = commaCount.length;
                                 functionObj.countOfParameter= countOfComma;
                             }

                             if(commaControl == false){
                                 if("Yok".equals(parameterValue)){
                                 countOfComma = 0;
                                 functionObj.countOfParameter=countOfComma;
                                 } 
                                 else functionObj.countOfParameter = 1;
                             }
                             //Bulunan degiskenler nesneye ataniyor.
                             functionObj.functionName = splitValue.substring(blank);
                             functionObj.returnValue = splitValue.substring(blank1,blank);

                             if(functionObj.returnValue.isEmpty()) functionObj.returnValue = "Yok";
                             //Istenilen cikti seklinde ekrana yazdiriliyor.
                             System.out.println("-------------");
                             System.out.println("Name:"+functionObj.functionName);
                             System.out.println("Return Type:"+functionObj.returnValue);
                             System.out.println("Count of Parameter:"+functionObj.countOfParameter);
                             System.out.println("Parameter:"+functionObj.parameterValue);
                        }
                }
            }
        // Try blogunda hata ile karsilasilirsa hata mesaji yazdiriliyor.
        catch (IOException e)  { 
            System.out.println("HATA ! ");
        }
    }      
} 
