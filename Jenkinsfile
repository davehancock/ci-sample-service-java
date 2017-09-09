pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
        gitVars = 'foo'
    }

    stages {

        stage('Package') {
            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }

            steps {
                echo "Hello"

                script {
                    echo gitVars
                    gitVars = checkout scm

                    sh './gradlew compileJava'
                    sh './gradlew assemble'

                    echo gitVars
                }
            }
        }

        stage('Package') {


            agent {
                dockerfile {
                    label "daves125125/ci-sample-service"
                    args "-v /tmp:/tmp -p 8000:8000"
                }
            }

            steps {

                echo gitVars
            }
        }

//        dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"

    }


}
