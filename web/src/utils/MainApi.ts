class MainApi {
  constructor(private baseUrl: string) { }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  private async getResponseDate(res: Response): Promise<never | any> {
    if (res.ok) {
      return res.json();
    }

    const error = await res.text();

    return Promise.reject(JSON.parse(error));
  }

  public async sigIn(login: string, password: string) {
    const res = await fetch(
      `${this.baseUrl}/auth/token`,
      {
        method: 'POST',
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({ login, password }),
      }
    )

    return this.getResponseDate(res);
  }

  public async sigUp(
    login: string,
    password: string,
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string
  ) {
    const res = await fetch(
      `${this.baseUrl}/auth/registration`,
      {
        method: 'POST',
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({
          login,
          password,
          firstName,
          lastName,
          email,
          phoneNumber
        }),
      }
    )

    return this.getResponseDate(res);
  }
}

const mainApi = new MainApi('http://192.168.0.106:9090')

export default mainApi;