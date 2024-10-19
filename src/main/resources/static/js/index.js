document.getElementById("ruleForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const ruleInput = document.getElementById("ruleInput").value;
    const payload = { rule: ruleInput };

    fetch("/api/v1/rules/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(response => {
        // Check if response is OK and return the correct format (JSON or text)
        if (response.ok) {
            return response.json(); // Handle the valid JSON response
        } else {
            return response.text(); // In case of error, handle as text
        }
    })
    .then(data => {
        if (data.rule) {
            document.getElementById("result").innerText = "Rule Created: " + data.rule;
        } else {
            document.getElementById("result").innerText = "Error: " + data;
        }
    })
    .catch(error => {
        console.error("Error:", error);
        document.getElementById("result").innerText = "Error creating rule.";
    });
});
