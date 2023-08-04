# CD Bank

**Welcome to the CD Bank API Development Project!**

In the ever-evolving world of technology, the banking sector has also shifted its services online, providing ease of access and convenience to their customers. As an upcoming software developer, it's crucial to be familiar with the development of these online systems. With that in mind, this project will be an exciting opportunity for you to develop practical skills in creating a fully functioning API for a hypothetical bank, the CD Bank.

In this project, you will focus on three major components: Accounts, Deposits, and Withdrawals. You will utilize the power of Java, paired with the Spring Framework, to create a RESTful API. This is an amazing chance to reinforce and demonstrate your knowledge in object-oriented programming, database management, server-side development, and application design.

The goal here isn't just to create a functioning system but to create one that is maintainable, scalable, and adheres to best practices in software development, particularly the SOLID principles. To ensure the robustness of your application, you will also need to write unit tests using JUnit for all the controllers and service implementations.

Remember, a well-planned project makes for a smooth development process. Take the time to visualize your system with sequence diagrams, capturing all the requirements and data flow for each component.

The path ahead is challenging, but the learning you'll gain will be invaluable. So, roll up your sleeves and let's get started on this journey of creating a banking API!

**CD Bank API Development**

Develop a RESTful API using Java and the Spring framework for a CD Bank. Your API will handle three components: Accounts, Deposits, and Withdrawals.

**Objective**

Your aim is to demonstrate your skills in object-oriented programming, database management, server-side development, and application design. You should write quality, maintainable code that sticks to the SOLID principles.

You also need to create JUnit tests for all controllers and service implementations.

**Planning**

Before starting with the code, make sure to plan. Create sequence diagrams for each component to understand system requirements, visualize data flow, and identify potential issues.

**Requirements**

1. **Accounts**: Create, read, update, and delete (CRUD) operations. Only valid customers should create accounts.

2. **Deposits**: CRUD operations.

3. **Withdrawals**: CRUD operations.

**Models**

1. **Account Model**: Properties include id, type, nickname, rewards, balance, and customer.

2. **Deposit Model**: Properties include id, type, transaction_date, status, payee, medium, amount, description.

3. **Withdrawal Model**: Properties include id, type, transaction_date, status, payer, medium, amount, description.

**Endpoints**

*Account Model*

* Create: POST `/api/v1/accounts`
* Retrieve: GET `/api/v1/accounts/{accountId}`
* Update: PUT `/api/v1/accounts/{accountId}`
* Delete: DELETE `/api/v1/accounts/{accountId}`

*Repeat for Deposit and Withdrawal Models.*

**Tasks**

For each model (Account, Deposit, Withdrawal):

1. Create the class with appropriate fields.
2. Implement Repository using Spring Data JPA.
3. Define Service Interface for business operations.
4. Implement the Service Interface.
5. Create Unit Test for the Service Implementation.
6. Build the Controller with necessary endpoints.
7. Implement a Controller Advice for error handling.
8. Create a Unit Test for the Controller.

Remember to apply SOLID principles throughout the development and to write meaningful unit tests for all services and controllers.

**Note**: Create a sequence diagram for each model before starting to code.

## Working on a team

Working in groups on a software project usually requires a good understanding of version control. Here's a step-by-step guide on how to work in separate branches and merge back to the main branch using Git.

**Step 1: Clone the repository**

Each group member should clone the repository to their local machine.

```bash
git clone <repository_url>
```

**Step 2: Create a new branch**

Each group member should create their own branch. The branch should be named relevant to the task or feature they are working on.

```bash
git checkout -b <branch-name>
```

**Step 3: Make changes**

Now, the group member can start making changes to the code on their branch.

**Step 4: Stage the changes**

When they have made some progress, they should stage the changes.

```bash
git add .
```

**Step 5: Commit the changes**

After staging, they need to commit the changes with a meaningful commit message.

```bash
git commit -m "Meaningful message about changes made"
```

**Step 6: Push the changes**

The changes need to be pushed to the remote repository.

```bash
git push origin <branch-name>
```

**Step 7: Create a pull request**

Once the group member has pushed their changes, they should go to the repository's page on GitHub (or other hosting service) and create a new pull request. This pull request should provide detailed information about the changes made.

**Step 8: Review the pull request**

Other group members should review the pull request, checking the changes for bugs or other issues.

**Step 9: Merge the pull request**

If there are no issues with the changes, the pull request can be merged into the main branch. This should be done on the repository's page on GitHub (or other hosting service).

**Step 10: Pull the latest changes**

After a pull request has been merged, every group member should pull the latest changes to their local main branch.

```bash
git checkout main
git pull
```

**Repeat**

These steps can be repeated for every new feature or task.

Remember, it's important to keep communication open with your group members and coordinate when pull requests are being merged. This can help prevent merge conflicts and other issues.

Also, ensure you are regularly pulling changes from the main branch to your working branch to stay up-to-date and avoid larger, more complex merge conflicts.

```bash
git checkout <branch-name>
git merge main
```

Happy coding!