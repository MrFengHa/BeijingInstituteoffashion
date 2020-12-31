<template>
  <div class="di main-wrap" v-loading="audio.waiting">
    <!-- 这里设置了ref属性后，在vue组件中，就可以用this.$refs.audio来访问该dom元素 -->
    <audio
      ref="audio"
      class="dn"
      :src="url"
      :preload="audio.preload"
      @play="onPlay"
      @error="onError"
      @waiting="onWaiting"
      @pause="onPause"
      @timeupdate="onTimeupdate"
      @loadedmetadata="onLoadedmetadata"
      @ended="onended"
      autoplay
    ></audio>
    <div style="width: 100%">
      <div class="audio-container">

        <img @click="startPlayOrPause" v-if="audio.playing && canPlay" class="btn" src="../../assets/images/pause.png"
             alt="">
        <img @click="startPlayOrPause" v-else-if="!audio.playing && canPlay" class="btn"
             src="../../assets/images/play.png" alt="">
        <img v-else-if="!canPlay" class="btn" src="../../assets/images/pause.png" alt="">

        <div class="time1">
          {{ audio.currentTime | formatSecond}}
        </div>

        <el-slider
          type="info"
          v-model="sliderTime"
          :format-tooltip="formatProcessToolTip"
          :show-tooltip="false"
          @change="changeCurrentTime"
          class="slider"
          :disabled=!type
        ></el-slider>

        <div class="time2">
          {{ audio.maxTime | formatSecond }}
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  function realFormatSecond(second) {
    var secondType = typeof second
    if (secondType === 'number' || secondType === 'string') {
      second = parseInt(second)
      var hours = Math.floor(second / 3600)
      second = second - hours * 3600
      var mimute = Math.floor(second / 60)
      second = second - mimute * 60
      return (
        ('0' + mimute).slice(-2) + ':' + ('0' + second).slice(-2)
      )
    } else {
      return '00:00'
    }
  }

  export default {
    name: "MyAudio",
    props: {
      src: {
        type: String,
        required: true
      },
      type: {
        type: Boolean,
        required: true
      }
    },
    data() {
      return {
        url: this.src,
        audio: {
          currentTime: 0,
          maxTime: 0,
          playing: false,
          muted: false,
          speed: 1,
          waiting: true,
          preload: 'auto'
        },
        sliderTime: 0,
        canPlay: true
      }
    },
    methods: {
      // 进度条toolTip
      formatProcessToolTip(index = 0) {
        index = parseInt((this.audio.maxTime / 100) * index)
        // return '进度条: ' + realFormatSecond(index)
      },
      // 播放跳转
      changeCurrentTime(index) {
        this.$refs.audio.currentTime = parseInt(
          (index / 100) * this.audio.maxTime
        )
        console.log(index)
      },
      startPlayOrPause() {
        return this.audio.playing ? this.pausePlay() : this.startPlay()
      },
      // 开始播放
      startPlay() {
        this.$refs.audio.play()
      },
      // 暂停
      pausePlay() {
        this.$refs.audio.pause()
      },
      // 当音频暂停
      onPause() {
        this.audio.playing = false
      },
      // 当发生错误, 就出现loading状态
      onError() {
        this.audio.waiting = true
      },
      // 当音频开始等待
      onWaiting(res) {
        // console.log(res);
      },
      // 当音频开始播放
      onPlay(res) {
        // console.log(res);
        this.audio.playing = true
        this.audio.loading = false
        let target = res.target
        let audios = document.getElementsByTagName('audio');
        [...audios].forEach(item => {
          if (item !== target) {
            item.pause()
          }
        })
      },
      // 当timeupdate事件大概每秒一次，用来更新音频流的当前播放时间
      onTimeupdate(res) {
        //   console.log('timeupdate')
        //   console.log(this.audio.maxTime)
        //   console.log(res.target.currentTime)
        this.audio.currentTime = res.target.currentTime
        this.sliderTime = parseInt(
          (this.audio.currentTime / this.audio.maxTime) * 100
        )
      },
      // 当加载语音流元数据完成后，会触发该事件的回调函数
      // 语音元数据主要是语音的长度之类的数据
      onLoadedmetadata(res) {
        //   console.log("loadedmetadata");
        //   console.log(res);
        //   console.log(res.target.duration)
        this.audio.waiting = false
        this.audio.maxTime = parseInt(res.target.duration)
      },
      // 当音频播放结束的时候
      onended(res) {
        // console.log(res)
        if (!this.type) {
          this.canPlay = false
        }
      }
    },
    filters: {
      formatSecond(second = 0) {
        return realFormatSecond(second)
      }
    },
    created() {
    },watch: {
      src(newValue,oldValue){
        this.url = newValue
      }
    }
  }
</script>

<style scoped>

  .main-wrap {
    padding: 10px 15px;
  }

  .slider {
    display: inline-block;
    width: 100%;
    top: 14px;
    margin-left: 15px;
    margin-right: 20px;
  }

  .di {
    display: inline-block;
  }

  .download {
    color: #409eff;
    margin-left: 15px;
  }

  .dn {
    display: none;
  }

  .audio-container {
    width: 83vw;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
  }

  .btn {
    height: 3vh;
    width: 5vw;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-right: 2vw;
    cursor: pointer;
  }

  .time1 {
    width: 42px;
    height: 22px;
    color: #999898;
    font-size: 16px;
    margin-right: 20px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }

  .time2 {
    width: 42px;
    height: 22px;
    color: #999898;
    font-size: 16px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
  }

  .audio-container >>> .el-slider__runway {
    margin-top: 14px;
  }
</style>
