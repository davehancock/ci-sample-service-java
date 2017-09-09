pipeline {
    agent none

    environment {
        GRADLE_USER_HOME = '/cache/gradle'
        HASH = 'unknown'
        IMAGE = 'daves125125/ci-sample-service:'
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
                script {
                    gitVars = checkout scm
                    HASH = gitVars["GIT_COMMIT"]
                }

                sh './gradlew compileJava'
                sh './gradlew assemble'
            }
        }

        stage('Docker Build') {

            agent { label "docker" }

            steps {
                sh """
                    docker build -t ${IMAGE} .
                    docker tag ${IMAGE} ${IMAGE}:${HASH}
                    docker push ${IMAGE}:${HASH}
                """
            }
        }
    }

}
