// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';

axios.defaults.baseURL = "http://39.102.41.207/"; // 关键步骤–填写后台请求统一的地址
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';


Vue.prototype.$http=axios;


Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.directive('title', {//单个修改标题
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }

})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})



