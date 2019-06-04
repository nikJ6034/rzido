import kakaoMap from "./kakao/kakaoMap";
import googleMap from "./google/googleMap";
export default{
    map : function(kind){
        if(kind == "kakao"){
            return kakaoMap;
        }else if(kind =="google"){
            return googleMap;
        }else{
            return kakaoMap;
        }
    }
}