export function callBackendAPI(url: string, method: string, body?: string) {
  return fetch(url, {
    method: method,
    body: body,
    headers: {
      'Content-Type': 'application/json'
    }
  });
}
