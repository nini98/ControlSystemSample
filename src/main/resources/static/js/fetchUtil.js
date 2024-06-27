const fetchUtil = {

    handleResponse: async (response) => {
        const jsonData = await response.json();
        if (!response.ok) {
            const error = new Error('Network response was not ok');
            error.header = jsonData.header;
            error.data = jsonData.data;
            throw error;
        }
        return jsonData;
    },

    get: async (url) => {
        try {
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    post: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    put: async (url, body) => {
        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    delete: async (url) => {
        try {
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },
};