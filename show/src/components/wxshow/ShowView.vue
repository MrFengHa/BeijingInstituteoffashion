<template>
  <div>
    <el-row>
      <el-col :span="22" :offset="1">
        <img src="../../assets/images/schoolTitle.png" class="schoolName">
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1">
        <div class="hallNameTitle">
          <h2 align="center"> {{hallName}}</h2>
        </div>
      </el-col>
    </el-row>


    <el-row>
      <el-col :span="22" :offset="1">
        <div style="text-align: center">
          <div :class="{cnNameClass:isCn,enNameClass:!isCn}">{{name}}</div>
        </div>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="22" :offset="1" style="text-align:center">
        <el-image class="elImage" alt="" :src=exhibits.imagePath :preview-src-list="srcList"></el-image>
      </el-col>
    </el-row>

    <div>
      <el-row>
        <el-col :span="11" :offset="1" style="background-color:#E5E5E5">
          <el-button class="button" @click="changeLanguage(1)">【中文】</el-button>
        </el-col>
        <el-col :span="11" style="background-color:#E5E5E5">
          <el-button class="button" @click="changeLanguage(-1)">【English】</el-button>
        </el-col>

      </el-row>
    </div>

    <el-row>
      <el-col :span="22" :offset="1">
        <my-audio :src=audio :type="true"></my-audio>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="22" :offset="1" style="text-align:center">
        <hr style="width:100%;"/>
      </el-col>
    </el-row>

    <el-row>

      <el-col :span="22" :offset="1" class="textName">

        <div :class="{textCnName:isCn,textEnName:!isCn}">{{name}}</div>

      </el-col>
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1" style="text-align:center">
        <hr style="width:100%;"/>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <br>
        <div style="width: 86vw;margin-left: 5vw;">
          <div style="font-size:large" v-html=desc>

          </div>
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
        audio: "",
        srcList: [],
        hallName: ''

      }
    }, methods: {
      changeLanguage(sign) {
        if (sign > 0) {
          this.name = this.exhibits.cnName;
          this.desc = this.exhibits.cnDesc.replace(/\n/g, '<br><br>');
          this.audio = this.exhibits.cnAudioPath;
          this.isCn = true;
          this.hallName = this.exhibits.exhibitionHall.cnName;
        } else if (sign < 0) {
          this.name = this.exhibits.enName;
          this.desc = this.exhibits.enDesc.replace(/\n/g, '<br><br>');
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
          _this.desc = res.data.cnDesc.replace(/\n/g, '<br><br>');
          _this.audio = res.data.cnAudioPath;
          _this.srcList.push(res.data.imagePath)
          _this.hallName = res.data.exhibitionHall.cnName;

        })
      } else {
        this.$http.get("exhibits/findById?number=001").then((res) => {
          _this.exhibits = res.data;
          _this.name = res.data.cnName;
          _this.desc = res.data.cnDesc.replace(/\n/g, '<br><br>');
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
    background-color: #A68961;
    width: 100%;
    height: 6vh;
    /*background-image: url(../../assets/images/nameTitle.png);*/
    border: 0;
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
    font-size: 4vw;
    text-align: center;

  }

  .textName div {
    vertical-align: middle;
    display: inline-block;
    overflow: hidden;
    height: 100%;


  }

  .textCnName {
    margin-top: 0.5vh;
    font-size: 6vw;
    font-weight: 650;
    width: 100%;
  }

  .textEnName {
    font-size: 5vw;
    font-weight: 650;
    width: 100%;
  }

  CnDesc {
    font-size: 20px;
  }

  EnDesc {
    font-size: 15px;
  }


</style>
