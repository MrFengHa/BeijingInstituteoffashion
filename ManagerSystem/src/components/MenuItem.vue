<template>
  <el-menu

    :default-active="this.$route.path"
    class="el-menu-vertical-demo menuHeight"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    router>

    <el-submenu index="/exhibits/">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span>展品管理</span>
      </template>
      <el-menu-item v-for="(item,i) in exhibitsList" :key="i" :index="item.path" >
        {{item.menuName}}
      </el-menu-item>

    </el-submenu>


  </el-menu>
</template>

<script>
  export default {
    name: "MenuItem",
    data() {
      return {
        exhibitsList: [
          {path: "/exhibits/exhibitsList", menuName: "展品列表"},
          {path: "/exhibitionhall/exhibitionhallList", menuName: "展厅列表"},

        ]


      }

    },
    methods: {


    }
  }
</script>

<style scoped>
  .menuHeight {
    height: 100%;

  }
</style>
