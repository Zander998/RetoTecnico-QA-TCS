import http from "k6/http";
import { check, sleep } from "k6";
import { SharedArray } from "k6/data";

// Carga de usuarios desde CSV
const users = new SharedArray("users", () =>
  open("../data/users.csv")
    .split("\n")
    .slice(1)
    .filter((line) => line.trim() !== "")
    .map((line) => {
      const [user, passwd] = line.trim().split(",");
      return { user, passwd };
    })
);

export const options = {
  // 👇 Sin el projectID, Grafana usará el destino por defecto de tu token 👇
  cloud: {
    name: 'Prueba de Login Fakestore',
  },
  stages: [
    { duration: "1m", target: 100 },
    { duration: "3m", target: 100 },
    { duration: "1m", target: 0 },
  ],
  thresholds: {
    http_req_duration: ["p(95)<1500"],
    http_req_failed: ["rate<0.03"],
  },
};

export default function () {
  const credentials = users[Math.floor(Math.random() * users.length)];

  const payload = JSON.stringify({
    username: credentials.user,
    password: credentials.passwd,
  });

  const res = http.post("https://fakestoreapi.com/auth/login", payload, {
    headers: { "Content-Type": "application/json" },
  });

  check(res, {
    "status is 201": (r) => r.status === 201,
    "response contains token": (r) => r.json("token") !== undefined,
    "response time < 1.5s": (r) => r.timings.duration < 1500,
  });

  sleep(1);
}