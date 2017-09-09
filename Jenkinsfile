pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
        HASH = 'unknown'
        BRANCH = 'unknown'
        IMAGE = 'daves125125/ci-sample-service'
    }

    stages {

        stage('Build') {
            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }

            steps {
                script {
                    gitVars = checkout scm
                    HASH = gitVars["GIT_COMMIT"]
                    BRANCH = gitVars["GIT_BRANCH"]
                }

                sh './gradlew compileJava'
                sh './gradlew assemble'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'openjdk:8-jdk'
                    args '-v /cache:/cache'
                }
            }

            steps {
                sh './gradlew test'
            }
        }

        stage('Docker Deploy Snapshot') {

            agent any

            steps {
                sh """
                    docker build -t ${IMAGE} .
                    docker tag ${IMAGE} ${IMAGE}:${HASH}
                    docker push ${IMAGE}:${HASH}
                """
            }
        }


        stage('Docker Deploy Release') {

            agent any

            when {
                expression {
                    return BRANCH == 'origin/master'
                }
            }

            steps {
                sh """
                    docker build -t ${IMAGE} .
                    docker tag ${IMAGE} ${IMAGE}:latest
                    docker push ${IMAGE}:latest
                """
            }
        }

    }

}
