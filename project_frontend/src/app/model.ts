export interface User {
    userId ?: string
    email : string
    password: number
}

export interface Question {
    question : string
    option1: string
    option2: string
    option3: string
    option4: string
}

export interface Questionnaire {
    questions : Question []
    userId : string
    questionnaireID : string
    dateCreated : string
}