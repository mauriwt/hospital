node {
    def installDeps = true
    def serverRepo = 'http://172.18.0.59/yachay/ytemplate_server.git'
    def clientRepo = 'http://172.18.0.59/yachay/ytemplate_client.git'
    def branch = 'alfa'
    def version = '0.0'
    stage('Install') {
        dir('client') {
            git branch: branch,
                url: clientRepo,
                credentialsId: '25cc2b90-340e-4748-9bf4-c41ee3cc6d77'
            if(installDeps)
                sh 'npm install'
        }
    }
    stage('Building') {
        dir('server') {
            git branch: branch,
                url: serverRepo,
                credentialsId: '25cc2b90-340e-4748-9bf4-c41ee3cc6d77'
            
            version = sh (
                script: 'git describe --tags',
                returnStdout: true
            ).trim()
            
            sh "/opt/gradle/gradle-3.4.1/bin/gradle angularBuild -PdeployEnv=${branch} -PangularDir=../client"
            sh "/opt/gradle/gradle-3.4.1/bin/gradle springPublish -PdeployEnv=${branch} -PangularDir=../client"
            
        }
    }
    stage('Code Analisis'){
      dir('client'){
        def scannerHome = tool 'sonarScanner';
              withSonarQubeEnv('SonarQube6.7.1') {
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectVersion=${version} "
              }
      }
      dir('server'){
        def scannerHome = tool 'sonarScanner';
              withSonarQubeEnv('SonarQube6.7.1') {
                sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectVersion=${version}"
              }
      }
    }
}
