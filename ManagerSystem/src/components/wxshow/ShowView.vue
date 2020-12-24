<template>
  <div>
    <div v-title :data-title=this.$route.query.number></div>
    <div style="background-color:#3A342E">
      <el-row>
        <el-col :span="9" :offset="2">
          <p class="number">{{no}}：<b>&nbsp;{{exhibits.number}}&nbsp;</b></p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="15">
          <el-image class="el-image" :src=exhibits.imagePath :preview-src-list="srcList"></el-image>
        </el-col>
        <el-col :span="6">
          <p :class="{cnName:isCn,enName:!isCn}">{{name}}</p></el-col>
        <el-col :span="2" :offset="1">
          <el-image style="width: 5px;"
                    src="http://39.102.41.207/static//image/right.png"></el-image>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="23" :offset="1">
          <p class="schoolName">{{schoolName}}</p>
        </el-col>
      </el-row>

      <el-row style="height: 30px" >
        <el-col :span="23" :offset="1">
          <b>{{hallName}}</b>
        </el-col>
      </el-row>
      <div>
        <el-row style="background-color: #FFFFFF">
          <el-col :span="12">
            <el-button class="button" @click="changeLanguage(1)">中文</el-button>
          </el-col>
          <el-col :span="12">
            <el-button class="button" @click="changeLanguage(-1)">English</el-button>
          </el-col>

        </el-row>
      </div>
    </div>


    <div>
      <audio :src=audio controls autoplay></audio>
    </div>
    <div>
      <h2>{{name}}</h2>
    </div>
    <div>
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
        name: "",
        desc: "",
        audio: "",
        srcList: [],
        isCn: true,
        schoolName: "北京服装学院民族服饰博物馆",
        hallName: ''

      }
    }, methods: {
      changeLanguage(sign) {
        if (sign > 0) {
          this.no = "编号";
          this.name = this.exhibits.cnName;
          this.desc = this.exhibits.cnDesc;
          this.audio = this.exhibits.cnAudioPath;
          this.isCn = true;
          this.schoolName = "北京服装学院民族服饰博物馆";
          this.hallName = this.exhibits.exhibitionHall.cnName;
        } else if (sign < 0) {
          this.no = "NO";
          this.name = this.exhibits.enName;
          this.desc = this.exhibits.enDesc;
          this.audio = this.exhibits.enAudioPath;
          this.isCn = false;
          this.schoolName = "Beijing Institute Of Fashion Technology";
          this.hallName = this.exhibits.exhibitionHall.enName;
        }
      },

    }
    ,
    beforeCreate() {
    }
    ,
    created() {
      let _this = this;
      if (this.$route.query.number != null) {
        this.$http.get("exhibits/findById?number=" + this.$route.query.number).then((res) => {
          _this.exhibits = res.data;
          _this.name = res.data.cnName;
          _this.desc = res.data.cnDesc;
          _this.audio = res.data.cnAudioPath;
          _this.srcList.push(res.data.imagePath)
          _this.hallName = res.data.exhibitionHall.cnName;
        })
      } else {
        this.$http.get("exhibits/findById?number=001").then((res) => {
          _this.exhibits = res.data;
          _this.name = res.data.cnName;
          _this.desc = res.data.cnDesc;
          _this.audio = res.data.cnAudioPath;
          _this.srcList.push(res.data.imagePath)

        })
      }

    },
  }
</script>

<style scoped>

  .number {
    color: #FFFFFF;
    font-size: 23px;
    height: 20px;
  }

  .number b {
    background-color: white;
    color: #3A342E;
  }

  .el-image {
    position: relative;
    align-items: center;
    left: 6%;
    width: 100%;
    float: left;
  }

  .cnName {
    color: white;
    font-size: 25px;
    writing-mode: tb-rl;
    height: 220px;
    margin-left: 19vw;
  }

  .enName {
    color: white;
    font-size: 15px;
    writing-mode: tb-rl;
    height: 220px;
    margin-left: 19vw;
    width: 6vw;
  }

  .schoolName {
    color: white;
  }


  .button {
    width: 100%;
  }

  pre {
    white-space: pre-wrap;
    white-space: -moz-pre-wrap;
    white-space: -pre-wrap;
    white-space: -o-pre-wrap;
    *word-wrap: break-word;
    *white-space: normal;
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
