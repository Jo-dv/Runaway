import axios from 'axios'

export default axios.create({
  //필요한것들 전달
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true //session사용
})
