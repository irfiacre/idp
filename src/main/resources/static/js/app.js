const signupForm = document.getElementById("signupForm");
const loginForm = document.getElementById("loginForm");
const statusBox = document.getElementById("status");

const signupTab = document.getElementById("showSignup");
const loginTab = document.getElementById("showLogin");

const switchToSignup = document.getElementById("switchToSignup");
const switchToLogin = document.getElementById("switchToLogin");

// Utility to show status
function showStatus(message, isSuccess = true) {
  statusBox.style.display = "block";
  statusBox.textContent = message;
  statusBox.className = isSuccess ? "success" : "error";
}

// Function to toggle forms
function showForm(form) {
  if (form === "login") {
    loginTab.classList.add("active");
    signupTab.classList.remove("active");
    loginForm.classList.add("active");
    signupForm.classList.remove("active");
  } else {
    signupTab.classList.add("active");
    loginTab.classList.remove("active");
    signupForm.classList.add("active");
    loginForm.classList.remove("active");
  }
  statusBox.style.display = "none";
}

// Tab switching
signupTab.addEventListener("click", () => showForm("signup"));
loginTab.addEventListener("click", () => showForm("login"));

// Switch links inside forms
switchToSignup.addEventListener("click", (e) => {
  e.preventDefault();
  showForm("signup");
});
switchToLogin.addEventListener("click", (e) => {
  e.preventDefault();
  showForm("login");
});

// Signup form submit
signupForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const body = Object.fromEntries(new FormData(e.target));

  try {
    const res = await fetch("/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });

    if (res.ok) {
      showStatus("✅ Signup successful!", true);
      showForm("login");
    } else {
      const errorMsg = await res.text();
      showStatus("❌ " + errorMsg, false);
    }
  } catch (err) {
    showStatus("❌ Network error", false);
  }
});

// Login form submit

//loginForm.addEventListener("submit", async (e) => {
  //e.preventDefault();
  //const body = Object.fromEntries(new FormData(e.target));

  //try {
    //const res = await fetch("/login", {
      //method: "POST",
//      headers: { "Content-Type": "application/json" },
  //    body: JSON.stringify(body),
    //});

//    if (res.ok) {
//      showStatus("✅ Login successful!", true);
//    } else {
//      const errorMsg = await res.text();
//      showStatus("❌ " + errorMsg, false);
//    }
//  } catch (err) {
//    showStatus("❌ Network error", false);
//  }
//});
