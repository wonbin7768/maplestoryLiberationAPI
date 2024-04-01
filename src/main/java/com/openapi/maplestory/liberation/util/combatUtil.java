package com.openapi.maplestory.liberation.util;

public class combatUtil {
    static public String transCombatType(Long combat){
        //API가 어제 기준으로 받아올수있기에 변환
        String trans[]=new String[]{" ","만","억"}; //돈 단위 출력
        int count=0; //몇 번째 money부터 출력할지 카운트하는 변수
        Long iNum= combat;
        Long arr[] = new Long[50];
        int i = 0; //배열의 맨 끝을 알기 위해 전역 변수로 선언
        for(i=0;iNum!=0;i++){
            arr[i]=iNum%10;
            iNum=iNum/10;
        }
        //거꾸로 출력하기 때문에 돈 단위의 끝 값을 구함
        for(int k = i-1; k >= 0; k--){
            if(k%4==0&& k!=0)	//4번마다 단위가 바뀜. 예를 들어 10000이면 money[1]부터 [0]까지 출력.
                count++;
        }
        //출력
        StringBuilder transCombatBuilder = new StringBuilder();
        boolean isStarted = false; // 숫자가 시작되었는지 여부를 나타내는 변수
        for(int k=i-1;k>=0;k--){
            if(arr[k] != 0){
                isStarted = true; // 숫자가 시작됨
            }
            if(isStarted){
                transCombatBuilder.append(arr[k]);
                if(k%4 == 0 && k != 0){
                    transCombatBuilder.append(trans[count]).append(" ");
                    count--;
                }
            }
        }
        String transCombat = transCombatBuilder.toString().trim();
        if(transCombat.isEmpty()){
            transCombat = "0"; // 숫자가 0인 경우 처리
        }
        System.out.println("transCombat = " + transCombat);
        return transCombat;
    }
}
