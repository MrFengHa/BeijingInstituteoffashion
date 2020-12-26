import Vue from 'vue'
import Router from 'vue-router'
import ShowView from "../components/wxshow/ShowView";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'ShowView',
      component: ShowView
    }
  ]
})
