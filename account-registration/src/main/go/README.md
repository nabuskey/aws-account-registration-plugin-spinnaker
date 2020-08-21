### Compile
To compile: 

`GOOS=linux CGO_ENABLED=0 GOARCH=amd64 go build -o /tmp/response` 

This will create an executable suitable for typical linux systems at `/tmp/response`

It expects a json file named `response.json` in the same directory that the executable is in. It will return its content with timestamp appended. 
Only one viable endpoint is provided (`localhost:8080/hello`). 

Example Dockerfile to run clouddriver and this program is provided here as well. To use it:
1. Clone the clouddriver repo and copy the `Dockerfile` in this repo to the clouddriver repo root.
2. In clouddriver repo run: `./gradlew --nodaemon -PenableCrossComilerPlugin=true clouddriver-web:installDist -x test`
3. Build the plugin and copy the zip file to clouddriver repo root.
4. Run `Docker build . -f Dockerfile`

JSON file should look something like this:

```json

{
  "Accounts": [
    {
      "AccountId": "259950518779",
      "SpinnakerAccountName": "mccloman-3",
      "Regions": [
        "us-west-2"
      ],
      "SpinnakerStatus": "ACTIVE",
      "SpinnakerAssumeRole": "role/spinnakerManaged",
      "SpinnakerProviders": [
        "ecs", "lambda", "ec2"
      ],
      "SpinnakerId": "spinnaker1",
      "CreatedAt": "1598026448909979587",
      "UpdatedAt": "1598027546352337694"
    }
  ],
  "Pagination": {
    "NextUrl": ""
  }
}
```