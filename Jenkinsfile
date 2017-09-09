pipeline {

//    def dockerBuild
//    def gitVars
//    env.GRADLE_USER_HOME = "/cache/gradle"

    agent none

    stages {
        stage('Package') {

            agent {
                docker 'openjdk:8-jdk'
                args['-v /cache:/cache']
            }

            steps {
                gitVars = checkout scm

                sh './gradlew compileJava'
                sh './gradlew assemble'

                dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
            }
        }

//        stage('Test') {
//            agent { docker 'openjdk:8-jdk' }
//            steps {
////                docker.image('openjdk:8-jdk').inside("-v /cache:/cache") {
//                sh './gradlew test'
////                }
//            }
//        }
//
//        stage('Deploy Snapshot') {
//            agent any
//            steps {
//                echo 'Deploying Snapshot...'
//                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-login') {
//                    dockerBuild.push()
//                }
//            }
//        }
//
//        // TODO need put something when its not master - "stage has not steps"
//        stage('Deploy Latest') {
//            agent any
//            when {
//                expression {
//                    return $ { gitVars["GIT_BRANCH"] } == 'origin/master'
//                }
//            }
//
//            steps {
//                docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-login') {
//                    echo 'Pushing docker images'
//                    dockerBuild.push 'latest'
//                }
//            }
//        }
    }

}
