import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MainPage from '@/components/MainPage'
import OverView from "@/components/page/OverView";
import AcademicCertificate from "@/components/page/AcademicCertificate";
import Competition from "@/components/page/Competition";
import SocialPracticeWorkStudy from "@/components/page/SocialPracticeWorkStudy";
import Volunteer from "@/components/page/Volunteer";


Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'MainPage',
            component: MainPage,
            redirect: '/overView',
            children: [
                {
                    path:'/overView',
                    name: 'OverView',
                    component: OverView
                },
                {
                    path: '/academicCertificate',
                    name: 'AcademicCertificate',
                    component: AcademicCertificate
                },
                {
                    path: '/competition',
                    name: 'Competition',
                    component: Competition
                },
                {
                    path: '/socialPracticeWorkStudy',
                    name: 'SocialPracticeWorkStudy',
                    component: SocialPracticeWorkStudy
                },
                {
                    path: '/volunteer',
                    name: 'Volunteer',
                    component: Volunteer
                }
            ]
        },
        {
            path: '/hello',
            name: "HelloWorld",
            component: HelloWorld
        }


    ]
})
