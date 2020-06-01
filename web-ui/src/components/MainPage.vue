<template>
  <div class="layout">
    <el-container style="height: 100%;">
      <el-header style="padding: 0">
        <el-menu :default-active="this.$route.path"
                 router
                 class="el-menu-demo"
                 mode="horizontal"
        >
          <!--background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"-->
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{item.navItem}}
          </el-menu-item>
        </el-menu>
      </el-header>
      <el-main style="height: 100%;padding-bottom: 0">
        <router-view></router-view>
      </el-main>
      <el-footer style="line-height: 60px;">
<!--        <strong>-->
          Copyright © <a href="https://github.com/crescent73" target="_blank">Crescent</a>. All rights reserved.
<!--        </strong>-->
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'MainPage',
  data () {
    return {
      activeIndex: '1',
      navList:[
        {name:'/overView',navItem:'概览'},
        {name:'/academicCertificate',navItem:'学术论文与技能证书'},
        {name:'/competition',navItem:'竞赛'},
        {name:'/socialPracticeWorkStudy',navItem:'社会实践与勤工助学'},
        {name:'/volunteer',navItem:'志愿服务'}
      ]
    }
  },
  methods: {
  }
}
</script>

<style scoped>

  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    height: 100%;
  }

</style>
