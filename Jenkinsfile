pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
        gitVars = 'foo'
        gitCommitVar = 'bar'
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
                    echo gitCommitVar

                    gitVars = checkout scm
                    gitCommitVar = gitVars["GIT_COMMIT"]

                    sh './gradlew compileJava'
                    sh './gradlew assemble'


                    echo gitVars["GIT_COMMIT"]
                    echo gitCommitVar
                }
            }
        }

        stage('Docker Build') {


            agent {
                dockerfile {
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
