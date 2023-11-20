import { useRouter } from 'vue-router'
import { defineStore } from 'pinia'
import http from '@/common/axios.js'
import { reactive, computed } from 'vue'

export const useAttractionStore = defineStore('attractionStore', () => {
  const router = useRouter()
  const attractionStore = reactive({
    // search
    ageFlag: false,
    indexFlag: true,
    connectionStatus: true,
    currentCity: 1,
    currentRegion: 0,
    cityList: [],
    regionList: [],
    resultDetail: [],
    resultListBest: [],
    resultListDay: [],
    resultListAge: [],

    // list
    resultList: [],
    city: 0, 
    region: 0,
    limit: 15,
    offset: 0,

    // pagination
    listRowCount: 15,  // 한 페이지에 나오는 데이터의 수 = limit
    pageLinkCount: 5,  // 페이지네비의 수
    currentPageIndex: 1,  // 초기 페이지
    totalListItemCount: 0,
  })

  const setAttractionList = (list) => attractionStore.resultList = list
  const setTotalListItemCount = (count) => attractionStore.totalListItemCount = count
  const setAttractionMovePage = (pageIndex) => {
    attractionStore.offset = (pageIndex - 1) * attractionStore.listRowCount
    attractionStore.currentPageIndex = pageIndex
  }

  // pagination
  const pageCount = computed(() => Math.ceil(attractionStore.totalListItemCount / attractionStore.listRowCount))  // 만들어낼 페이지 버튼 수
  const startPageIndex = computed(() => {
    if (attractionStore.currentPageIndex % attractionStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (attractionStore.currentPageIndex / attractionStore.pageLinkCount - 1) * attractionStore.pageLinkCount + 1;
    } else {
      return Math.floor(attractionStore.currentPageIndex / attractionStore.pageLinkCount) * attractionStore.pageLinkCount + 1;
    }
  })

  const endPageIndex = computed(() => {
    if (attractionStore.currentPageIndex % attractionStore.pageLinkCount == 0) {
      //10, 20...맨마지막
      return (attractionStore.currentPageIndex / attractionStore.pageLinkCount - 1) * attractionStore.pageLinkCount + attractionStore.pageLinkCount;
    } else {
      return Math.floor(attractionStore.currentPageIndex / attractionStore.pageLinkCount) * attractionStore.pageLinkCount + attractionStore.pageLinkCount;
    }
  })

  const prev = computed(() => attractionStore.currentPageIndex <= attractionStore.pageLinkCount ? false : true)
  const next = computed(() => Math.floor(pageCount / attractionStore.pageLinkCount) * attractionStore.pageLinkCount < attractionStore.currentPageIndex ? false : true)

  const getCity = async() => {
    try {
      let { data } = await http.get('/trip')
      attractionStore.cityList = data
    } catch (error) {
      console.log(error)
    }
  }

  const getRegion = async(sidoCode) => {
    attractionStore.regionList = [] // Reset to empty array
    try {
      let { data } = await http.get(`/trip/getRegion/${sidoCode}`)
      attractionStore.regionList = data
    } catch (error) {
      console.log(error)
    }
  }

  const search = async(sidoCode, gugunCode) => {
    if(attractionStore.currentCity != sidoCode || attractionStore.currentRegion != gugunCode) {  // 조회 대상이 변경되면 페이지네이션 초기화
      attractionStore.currentPageIndex = 1
      attractionStore.offset = 0
    }
    attractionStore.currentRegion = attractionStore.currentCity != sidoCode ? 0 : gugunCode  // 현재 도시가 변경되면 지역 코드를 0(전체)으로 변경, 아니면 유지
    attractionStore.currentCity = sidoCode
    let params = {
      sidoCode: sidoCode, 
      gugunCode: gugunCode,
      limit: attractionStore.limit,
      offset: attractionStore.offset,
   }
    try {
      let { data } = await http.get('/trip/search/', { params })
      console.log("boardStore: data : ");
      console.log(data);
      setAttractionList(data.list);
      setTotalListItemCount(data.count);
      attractionStore.connectionStatus = true
    } catch (error) {
        attractionStore.connectionStatus = false
      console.log(error)
    }
  }

  const searchDetail = async(contentId) => {  // SearchResult 페이지에서 인자를 넘겨받은 상태
    try {
      let {data} = await http.get('/trip/searchDetail/' + contentId)
      attractionStore.connectionStatus = true
      attractionStore.resultDetail = data
    } catch(error) {
      attractionStore.connectionStatus = false
      console.log(error)
    }
  }
  

  const searchPopular = async () => {
    try {
      let { data } = await http.get('/trip/searchPopular')
      attractionStore.resultListBest = data
    } catch(error) {
      console.log(error)
    }
  }

  const searchPopularDay = async () => {
    try {
      let { data } = await http.get('/trip/searchPopularDay')
      attractionStore.resultListDay = data
    } catch(error) {
      console.log(error)
    }
  }
  
  const searchPopulaAge = async () => {
    try {
      let { data } = await http.get('/trip/searchPopularAge')
      attractionStore.resultListAge = data
      attractionStore.ageFlag = true
    } catch(error) {
      console.log(error)
    }
  }

  const goDetail = async (contentId) => {
    router.push({
      name: 'detail',
      params: { id: contentId }
    })
  }

  return {
    pageCount, startPageIndex, endPageIndex, prev, next, setAttractionList, setAttractionMovePage, setTotalListItemCount,
    attractionStore, getCity, getRegion, search, searchDetail, searchPopular, searchPopularDay, searchPopulaAge, goDetail
  }
})