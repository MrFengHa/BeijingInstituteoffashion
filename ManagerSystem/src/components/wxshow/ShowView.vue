<template>
  <div>
    <div v-title :data-title=this.$route.query.number></div>
    <el-row>
      <el-col :span="12">{{no}}：{{exhibits.number}}</el-col>
      <el-col :span="12">{{nameTile}}:{{name}}</el-col>

    </el-row>
    <div class="imageDiv">
      <el-image class="el-image" :src=exhibits.imagePath :preview-src-list="srcList"> </el-image>
    </div>
    <div>
      <el-button class="button" @click="changeLanguage(1)">中文</el-button>
      <el-button class="button" @click="changeLanguage(-1)" style="margin-left: -1.2%">English</el-button>
    </div>
    <div>
      <audio :src=audio controls autoplay></audio>
    </div>
    <div>
      <h2>{{name}}</h2>
    </div>
    <div >
      <pre style="font-size:12px;">
      {{desc}}
    </pre>
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
        no: "编号",
        nameTile: "名称",
        name: "",
        desc:"",
        audio:"",
        srcList:[]

      }
    }, methods: {
      changeLanguage(sign) {
        if (sign>0){
          this.no = "编号";
          this.nameTile = "名称";
          this.name = this.exhibits.cnName;
          this.desc = this.exhibits.cnDesc;
          this.audio = this.exhibits.cnAudioPath;
          console.log(this.desc)
        }else if(sign<0){
          this.no = "NO";
          this.nameTile = "NAME";
          this.name = this.exhibits.enName;
          this.desc = this.exhibits.enDesc;
          this.audio = this.exhibits.enAudioPath;
          console.log(this.desc)
        }
      },

    }
    ,
    beforeCreate() {
    }
    ,
    created() {
      let _this = this;
      if ( this.$route.query.number!=null){
        this.$http.get("manager/findById?number=" + this.$route.query.number).then((res) => {
          _this.exhibits = res.data;
          _this.name = res.data.cnName;
          _this.desc = res.data.cnDesc;
          _this.audio = res.data.cnAudioPath;
          _this.srcList.push( res.data.imagePath)

        })
      }else {
        this.$http.get("manager/findById?number=001").then((res) => {
          _this.exhibits = res.data;
          _this.name = res.data.cnName;
          _this.desc = res.data.cnDesc;
          _this.audio = res.data.cnAudioPath;
          _this.srcList.push( res.data.imagePath)

        })
      }

    },
  }
</script>

<style scoped>

  .imageDiv {
    background-color: #D9EBED;
    height: 30vh;
    margin: auto;
    display: flex;
    align-items: center

  }

  .el-image {
    position: relative;
    align-items: center;
    left: 25%;
    width: 50%;
  }

  .button{
    width: 50%;
  }

  pre {
    white-space: pre-wrap;
    white-space: -moz-pre-wrap;
    white-space: -pre-wrap;
    white-space: -o-pre-wrap;
    *word-wrap: break-word;
    *white-space : normal ;
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
