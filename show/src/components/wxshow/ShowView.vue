<template>
  <div>
    <div v-title :data-title=this.$route.query.number></div>
    <img src="../../assets/images/schoolTitle.png" class="schoolName">
    <el-row>
      <el-col :span="24">
        <div class="hallNameTitle">
          <h2  align="center"> {{hallName}}</h2>
        </div>
      </el-col>
    </el-row>


    <el-row>
      <el-col :span="24">
        <div style="text-align: center">
          <div :class="{cnNameClass:isCn,enNameClass:!isCn}">{{name}}</div>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="24" style="text-align:center">
        <el-image class="elImage" alt="" :src=exhibits.imagePath :preview-src-list="srcList"></el-image>
      </el-col>
    </el-row>

    <div>
      <el-row style="background-color:#E5E5E5">
        <el-col :span="12">
          <el-button class="button" @click="changeLanguage(1)">【中文】</el-button>
        </el-col>
        <el-col :span="12">
          <el-button class="button" @click="changeLanguage(-1)">【English】</el-button>
        </el-col>

      </el-row>
    </div>

    <el-row>
      <el-col :span="24">
        <my-audio :src=audio :type="true"></my-audio>
      </el-col>
    </el-row>


    <el-row>
      <hr style="width:100%;"/>
      <el-col :span="24" class="textName" >
<!--        class="textName"-->
        <!--        <p :class="{textCnName:isCn,textEnName:!isCn}">{{name}}</p>-->
        <div :class="{textCnName:isCn,textEnName:!isCn}">{{name}}</div>
      </el-col>
      <hr style="width:100%;"/>
      <el-col>

        <div style="width: 86vw;margin-left: 5vw;" >
           <pre style="font-size:large">
         {{desc}}
        </pre>
        </div>


      </el-col>
    </el-row>


  </div>

</template>

<script>
  import MyAudio from "../Audio/MyAudio";

  export default {
    name: "ShowView",
    components: {MyAudio},
    data() {
      return {
        exhibits: {},
        isCn: true,
        no: "编号",
        name: "",
        desc: "",
        audio: "http://39.102.41.207/0001/cn.mp3",
        srcList: [],
        hallName: ''

      }
    }, methods: {
      changeLanguage(sign) {
        if (sign > 0) {
          this.name = this.exhibits.cnName;
          this.desc = this.exhibits.cnDesc;
          this.audio = this.exhibits.cnAudioPath;
          this.isCn = true;
          this.hallName = this.exhibits.exhibitionHall.cnName;
        } else if (sign < 0) {
          this.name = this.exhibits.enName;
          this.desc = this.exhibits.enDesc;
          this.audio = this.exhibits.enAudioPath;
          this.isCn = false;
          this.hallName = this.exhibits.exhibitionHall.enName;
        }
      },

    }
    ,
    beforeCreate() {
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

    watch: {
      name() {

      }
    }
  }
</script>

<style scoped>
  .schoolName {
    width: 100%;
  }

  .elImage {
    position: relative;
    align-items: center;
    width: 75%;
  }

  .hallNameTitle {
    background-image: url(../../assets/images/nameTitle.png);
    width: 97vw;
    height: 8vh;
    background-repeat: no-repeat;
    background-size: 96vw 8.5vh;
    border: 0;
    background-color: transparent;
    text-align: center;
    color: white;
    display: table;
  }

  .hallNameTitle h2 {
    display: table-cell;
    vertical-align: middle;
  }

  .cnNameClass {
    font-size: 7vw;
    font-weight: 650;
  }

  .enNameClass {
    font-size: 5vw;
    font-weight: 650;
  }


  .button {
    width: 100%;
    color: black;
    background-color: #E5E5E5;
  }

  .textName {
    /*background-image: url(../../assets/images/textTitle.png);*/
    /*width: 97vw;*/
    /*height: 6.2vh;*/
    /*background-repeat: no-repeat;*/
    /*background-size: 96vw 6vh;*/
    /*border: 0;*/
    /*background-color: transparent;*/
    text-align: center;
    color: black;
    display: table;
    margin: auto;
  }

  .textName div {
    vertical-align: middle;
    display: inline-block;
    overflow: hidden;
    height: 100%;


  }

  .textCnName {
    margin-top: 0.5vh;
    font-size: 7vw;
    font-weight: 650;
    width: 80%;
  }

  .textEnName {
    font-size: 6vw;
    font-weight: 650;
    width: 80%;
  }

  CnDesc {
    font-size: 20px;
  }

  EnDesc {
    font-size: 15px;
  }

  pre {
    white-space: pre-wrap;
    white-space: -moz-pre-wrap;
    white-space: -o-pre-wrap;
    *word-wrap: break-word;
    *white-space: normal;
  }


</style>
