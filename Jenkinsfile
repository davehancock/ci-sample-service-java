node {

    env.GRADLE_USER_HOME="/tmp"

    def dockerBuild
    stage('Main build') {

        def gitVars = checkout scm

        // https://go.cloudbees.com/docs/cloudbees-documentation/cje-user-guide/index.html#docker-workflow
        docker.image('openjdk:8-jdk').inside {
            sh './gradlew clean test'
            sh './gradlew build'
            sh 'pwd'
            sh 'ls -ltra ./build/libs'
        }

        sh 'pwd'
        sh 'ls -ltra ./build/libs'

        dockerBuild = docker.build "daves125125/ci-sample-service:${gitVars["GIT_COMMIT"]}"
    }

    stage('Test') {
        docker.image('bash').inside {
            echo "Hello World"
            sh 'pwd'
            sh 'ls -ltra ./build/libs'
        }
    }

    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-login') {

        stage('Deploy') {
            echo "Starting Deploy"
            dockerBuild.push()
            dockerBuild.push 'latest'
        }
    }

}
