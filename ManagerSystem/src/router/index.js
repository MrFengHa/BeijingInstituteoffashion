import Vue from 'vue'
import Router from 'vue-router'
import LoginView from "../components/LoginView";
import MainView from "../components/MainView";
import ExhibitsListView from "../components/exhibits/ExhibitsListView";
import ExhibitsManager from "../components/exhibits/ExhibitsManager";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainView',
      component: MainView,
      children:[
        {path:"/exhibits/exhibitsList",component:ExhibitsListView

        },
        {path:"/exhibits/exhibitsToAdd",component:ExhibitsManager},
        {path:"/exhibits/exhibitsToUpdate",component:ExhibitsManager}
        ]
    },

  ]
})

// const VueRouterPush = Router.prototype.push
// Router.prototype.push = function push (to) {
//   return VueRouterPush.call(this, to).catch(err => err)
// }

