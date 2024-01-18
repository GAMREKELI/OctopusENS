import { ILoginFormInputs, IRegistrationFormInputs } from "../types";

class MainApi {
  constructor(private baseUrl: string) { }

  private async getResponseDate(res: Response): Promise<never | unknown> {
    if (res.ok) {
      return await res.json();
    }

    // const error = await res.text();

    return Promise.reject(res);
  }

  private setPostOptions<T>(data: T): object {
    return {
      method: 'POST',
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify(data),
    }
  }

  public async sigIn(data: ILoginFormInputs) {
      const res = await fetch(
        `${this.baseUrl}/auth/token`,
        this.setPostOptions(data),
      );

      return this.getResponseDate(res);
  }

  public async sigUp(data: IRegistrationFormInputs) {
    const res = await fetch(
      `${this.baseUrl}/auth/registration`,
      this.setPostOptions(data),
    );

    return this.getResponseDate(res);
  }
}

const mainApi = new MainApi('http://localhost:9090');

export default mainApi;
