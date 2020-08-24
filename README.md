
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
TEST _HOOKS
YESGHH_SJKHDKJ
HOOKSS
