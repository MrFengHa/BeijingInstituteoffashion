<template>
  <div>
    <div>
      <!--      <el-image-->
      <!--        class="imageClass"-->
      <!--        src="../assets/logo.png"-->
      <!--        :preview-src-list="srcList">-->
      <!--      </el-image>-->

      <img :src="imgURL" class="imageClass">
    </div>
    <div style="margin: auto;width: 50%;">
      <el-button type="primary" @click="changeLanguage(1)" style="background-image:url('../assets/藏族八瑞宝.jpg') ">中文</el-button>
      <el-button type="success" @click="changeLanguage(-1)">English</el-button>
    </div>

    <div id="audioParent">

      <audio loop="true" autoplay controls :src="audioURL" class="audio">

      </audio>
    </div>
  </div>

</template>

<script>
  export default {
    name: "Main",
    data() {
      return {
        id: "",
        audioURL: "http://39.102.41.207/001/cn.mp3",
        imgURL:"http://39.102.41.207/001/image.jpg"

      }
    }, methods: {
      changeLanguage(sign) {
        if (sign > 0) {
          this.audioURL = "http://39.102.41.207/001/cn.mp3"
        } else {
          this.audioURL = "http://39.102.41.207/001/en.mp3"
        }
      },

    }
    ,
    created() {

      this.id = this.$route.params.id;
      console.log(this.id)
    },
  }
</script>

<style scoped>
  .imageClass {
    margin-left: -2.1vw;
    margin-top: -1.1vh;
    width: 100vw;
    height: 28vh;
  }

  #audioParent{
    margin: auto;
    width: 90%;
  }
  .audio{

    background: no-repeat center bottom;
    background-size:auto;
  }


</style>
