import React from "react";
import "../cssfile/Register.css";

function RegisterPageContainer() {
  return (
    <div>
      <form>
        <div className="register-content">
          <h1>Welcome to PSW</h1>
        </div>

        <div className="mb-3">
          <label for="exampleInputEmail1" className="form-label">
            Email address
          </label>
          <input
            type="email"
            className="form-control"
            id="exampleInputEmail1"
            placeholder="Enter email"
            aria-describedby="emailHelp"
            required
          />
        </div>
        <div className="mb-3">
          <label for="exampleInputPassword1" className="form-label">
            Password
          </label>
          <input
            type="password"
            className="form-control"
            placeholder="Create Password"
            id="exampleInputPassword1"
            required
          />
        </div>

        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default RegisterPageContainer;
