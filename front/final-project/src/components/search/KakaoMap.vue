<template>
    <div id="map"></div>
</template>
  
<script setup>
    import { onMounted, watchEffect } from 'vue';
    const props = defineProps(['latitude', 'longitude']);
    onMounted(() => {
        if (window.kakao && window.kakao.maps) {  // api를 불러오고 나서 지도 생성
            watchEffect(() => { 
                if (props.latitude !== undefined && props.longitude !== undefined) 
                    initKakaoMap();
            });
        }
        else
            initScript();
    });

    function initScript() {  // api 호출
        const script = document.createElement('script');
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?appkey=b5056eedcfa109209cf287e553cab19e&libraries=services,clusterer,drawing&autoload=false';
        script.async = true;
        script.onload = () => kakao.maps.load(initKakaoMap);
        document.head.appendChild(script);
    }

    function initKakaoMap() {
        const container = document.getElementById('map');
        const options = {
        center: new kakao.maps.LatLng(props.latitude, props.longitude),
        level: 3,
        };
        const map = new kakao.maps.Map(container, options);
        const markerPosition = new kakao.maps.LatLng(props.latitude, props.longitude);
        const marker = new kakao.maps.Marker({
        position: markerPosition,
        });

        marker.setMap(map);
    }
</script>
  
<style scoped>
#map {
    width: 100%; 
    height: 550px
}
</style>
  