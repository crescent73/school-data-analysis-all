import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import echarts from 'echarts'

import api from './api/request'




Vue.use(echarts)
Vue.use(ViewUI);
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$api = api;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
