
```yaml
credentials:
  system:
    domainCredentials:
      - credentials:
          - basicSSHUserPrivateKey:
              scope: GLOBAL
              id: "basic-SSH"
              username: "ssh-username"
              passphrase: ""
              description: "SSH Credentials for ssh-username"
              privateKeySource:
                directEntry:
                  privateKey: ${SSH_PRIVATE_KEY}
          - usernamePassword:
              scope: GLOBAL
              id: "username"
              username: "some-user"
              password: ${SomeUserPassword}
              description: "Username/Password Credentials for some-user"
          - string:
              scope: GLOBAL
              id: "secret-text"
              secret: ${SecretText}
              description: "Secret Text"
          - aws:
              scope: GLOBAL
              id: "AWS"
              accessKey: ${AWS_ACCESS_KEY_ID}
              secretKey: ${AWS_SECRET_ACCESS_KEY}
              description: "AWS Credentials"
          - file:
              scope: GLOBAL
              id: "secret-file"
              fileName: "mysecretfile.txt"
              secretBytes: ${SECRET_FILE_BYTES} # SECRET_FILE_BYTES="$(cat mysecretfile.txt | base64)"
```

## ARTIFICAT
```yaml
unclassified:
  artifactorybuilder:
    useCredentialsPlugin: true
    artifactoryServers:
      - serverId: artifactory
        artifactoryUrl: http://acme.com/artifactory
        deployerCredentialsConfig:
          credentialsId: "artifactory"
        resolverCredentialsConfig:
          username: artifactory_user
          password: ${ARTIFACTORY_PASSWORD}
          
```

## SSH CREDENTIALS
```yaml
credentials:
  system:
    domainCredentials:
      - credentials:
          - basicSSHUserPrivateKey:
              scope: SYSTEM
              id: ssh_with_passphrase_provided
              username: ssh_root
              passphrase: ${SSH_KEY_PASSWORD}
              description: "SSH passphrase with private key file. Private key provided"
              privateKeySource:
                directEntry:
                  privateKey: ${SSH_PRIVATE_KEY}
```

## JSCOS
```yaml
credentials:
  system:
    domainCredentials:
    - credentials:
      - usernamePassword:
          description: ""
          id: "github-user1"
          password: ${github_pass}
          scope: SYSTEM
          username: ${github_user}
      - usernamePassword:
          description: "admin_2"
          id: "admin_2"
          password: ${GIT_USER}
          scope: GLOBAL
          username: ${GIT_PASSWORD}
      - basicSSHUserPrivateKey:
          id: "SSH_KEY_1"
          privateKeySource:
            directEntry:
              privateKey: "{AQAAABAAAAAQBefw0aT3YvLvHaUTTUFMN6Q6bfzL139cScuoW3lEV2M=}"
          scope: GLOBAL
          username: "root"
      - basicSSHUserPrivateKey:
          id: "SSH_KEY_1_System"
          privateKeySource:
            directEntry:
              privateKey: "{AQAAABAAAAAQUdLgCrzihqyaZnsxdSzujEErIvIN/bFMoHoWA2KFqm4=}"
          scope: SYSTEM
          username: "root"
      - string:
          description: "Secret_test_1"
          id: "Secret_test_1"
          scope: GLOBAL
          secret: "{AQAAABAAAAAQ64NYthA7iWRFsxGtJHMmCWDvnTnIM/gZLVb/f51RLBo=}"
```

### artifactory plugin
- Artifactory plugin configuration belongs under unclassified root element

```yaml
unclassified:
  artifactorybuilder:
    useCredentialsPlugin: true
    artifactoryServers:
      - serverId: artifactory
        artifactoryUrl: http://acme.com/artifactory
        deployerCredentialsConfig:
          credentialsId: "artifactory"
        resolverCredentialsConfig:
          username: artifactory_user
          password: ${ARTIFACTORY_PASSWORD}

```

```yaml
unclassified:
  artifactorybuilder:
    useCredentialsPlugin: true
    artifactoryServers:
      - serverId: my-artifactory
        artifactoryUrl: http://localhost:8081/repository/RELEASE/
        deployerCredentialsConfig:
          credentialsId: "artifactory-id"
        resolverCredentialsConfig:
          username: admin
          password: ${ARTIFACTORY_PASSWORD}

```

