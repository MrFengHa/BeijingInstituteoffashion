<template>
  <div>
    <div v-title :data-title=this.$route.query.number></div>
    <el-row >
      <el-col :span="6">{{no}}：</el-col>
      <el-col :span="6">{{exhibits.number}}</el-col>
      <el-col :span="6">{{nameTile}}</el-col>
      <el-col :span="6">{{name}}</el-col>
    </el-row>
    <div class="imageDiv" >
      <el-image class="el-image" :src=exhibits.imagePath></el-image>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ShowView",

    data() {
      return {
        exhibits: {},
        isCn: true,
        no:"编号",
        nameTile:"名称",
        name:""


      }
    }, methods: {
      changeLanguage(sign) {

      },

    }
    ,
    beforeCreate() {
    }
    ,
    created() {
      let _this = this;
      this.$http.get("manager/findById?number=" + this.$route.query.number).then((res) => {
        _this.exhibits = res.data;
        _this.name = res.data.cnName;
        console.log(_this.exhibits)
      })
    },
  }
</script>

<style scoped>
  .imageDiv{
    background-color:#D9EBED ;
    height: 20vh;
    margin: auto;

  }
  .el-image{
    position:relative;
    left:50%;
  }

  #audioParent {
    margin: auto;
    width: 90%;
  }

  .audio {

    background: no-repeat center bottom;
    background-size: auto;
  }


</style>
