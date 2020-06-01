import anxios from "@/api/http";
import qs from 'qs';


const proxy = '/api';

const main = {
    nationAnalysis(params){
        return anxios.get( proxy + '/main/nation_analysis',qs.stringify(params))
    },
    birthplaceAnalysis(params){
        return anxios.post(proxy+'/main/birthplace_analysis',qs.stringify(params))
    },
    genderAnalysis(params){
        return anxios.post(proxy+'/main/gender_analysis',qs.stringify(params))
    },
    levelAnalysis(params){
        return anxios.post(proxy+'/main/level_analysis',qs.stringify(params))
    }
};

const academic = {
    academyAnalysis(params){
        return anxios.get( proxy + '/academic/academy_analysis',qs.stringify(params))
    },
    authoringTypeAnalysis(params){
        return anxios.get( proxy + '/academic/authoring_type_analysis',qs.stringify(params))
    }
}

const certificate = {
    academyAnalysis(params){
        return anxios.get( proxy + '/certificate/academy_analysis',qs.stringify(params))
    },
    typeAnalysis(params){
        return anxios.get( proxy + '/certificate/type_analysis',qs.stringify(params))
    },

};

const competition = {
    analysis(params){
        return anxios.get( proxy + '/competition/analysis',qs.stringify(params))
    },
    levelAnalysis(params){
        return anxios.get( proxy + '/competition/level_analysis',qs.stringify(params))
    },
    ranking(params){
        return anxios.get( proxy + '/competition/ranking',qs.stringify(params))
    },
};

const patent = {
    academyAnalysis(params){
        return anxios.get( proxy + '/patent/academy_analysis',qs.stringify(params))
    },
    typeAnalysis(params){
        return anxios.get( proxy + '/patent/type_analysis',qs.stringify(params))
    },
};

const socialPractice = {
    projectAnalysis(params){
        return anxios.get( proxy + '/social_practice/project_analysis',qs.stringify(params))
    },
    typeAnalysis(params){
        return anxios.get( proxy + '/social_practice/type_analysis',qs.stringify(params))
    },
};

const volunteer = {
    academyParticipationAnalysis(params){
        return anxios.get( proxy + '/volunteer/academy_participation_analysis',qs.stringify(params))
    },
    gradeAnalysis(params){
        return anxios.get( proxy + '/volunteer/grade_analysis',qs.stringify(params))
    },
    gradeParticipationAnalysis(params){
        return anxios.get( proxy + '/volunteer/grade_participation_analysis',qs.stringify(params))
    },
    levelAnalysis(params){
        return anxios.get( proxy + '/volunteer/level_analysis',qs.stringify(params))
    },
};

const workStudy = {
    jobAnalysis(params){
        return anxios.get( proxy + '/work_study/job_analysis',qs.stringify(params))
    },
    participationAnalysis(params){
        return anxios.get( proxy + '/work_study/participation_analysis',qs.stringify(params))
    },
};

export default {
    main,
    academic,
    certificate,
    competition,
    patent,
    socialPractice,
    volunteer,
    workStudy
}