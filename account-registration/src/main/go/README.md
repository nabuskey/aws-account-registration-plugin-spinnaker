### Compile
To compile: 

`GOOS=linux CGO_ENABLED=0 GOARCH=amd64 go build -o /tmp/response` 

This will create an executable suitable for typical linux systems at `/tmp/response`

It expects a json file named `response.json`. It will return its content with timestamp appended. 
Only one viable endpoint is provided (`localhost:8080/hello`). 

Example Dockerfile to run clouddriver and this program is provided here as well. 

The plugin should be configured with this.
```yaml
accountProvision:
  url: 'http://localhost:8080/hello'
```

JSON file should look something like this:

```json

{
  "Accounts": [
    {
      "AccountId": "<>",
      "AccountArn": "<>",
      "AccountEmail": "<>",
      "AccountName": "<>",
      "Environment": "dev|stg|prd",
      "Profile": "mcp|des",
      "Regions": [
        "<>"
      ],
      "ServiceId": "<>",
      "Status": "ACTIVE|SUSPENDED",
      "SpinnakerAssumeRole": "<>",
      "SpinnakerProviders": [
        "<>"
      ],
      "SpinnakerEnabled": true,
      "CreatedAt": "<>",
      "UpdatedAt": "<>"
    }
  ],
  "Pagination": {
    "Limit": "<>",
    "NextUrl": "<>",
    "CursorState": "<>"
  }
}
```