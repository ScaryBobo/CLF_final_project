export interface User {
    userId ?: string
    email : string
    password: number
}
export interface Survey {
    surveyId : string
    userId : string
    surveyTitle : string
    dateCreated : string
    questions: Question []
}
export interface Question {
    surveyId : string
    questionText : string
    questionId : string
    answers : Answer []
}

export interface Answer {
    answerId : string
    answerText : string
    questionId : string
}

export interface Attempt {
    surveyId ?: string
    userId ?: string
    attemptId?: string
    answeredSurveys ?: AnsweredSurvey[] 
}

export interface AnsweredSurvey {
    attemptId ?: string
    questionId ?: string
    answerId ?: string
}



